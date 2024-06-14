package com.filemanagement.filemanagement.service

import com.filemanagement.filemanagement.dto.CreateUserDTO
import com.filemanagement.filemanagement.dto.UserDTO
import com.filemanagement.filemanagement.dto.UserMapper
import com.filemanagement.filemanagement.exception.UserNotFoundException
import com.filemanagement.filemanagement.model.Role
import com.filemanagement.filemanagement.model.User
import com.filemanagement.filemanagement.repository.UserRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = findUserByUsername(username!!)
        return org.springframework.security.core.userdetails.User(user.username, user.password, emptyList())

    }
    internal fun findUserByUsername(username: String): User {
        return userRepository.findByUsername(username) ?: throw UserNotFoundException("User not found with username: $username")
    }

    @Scheduled(cron = "0 0 0 * * MON")
    @Transactional
    fun rotateUserRoles() {
        val users = userRepository.findAll().sortedBy { it.id }
        if (users.isEmpty()) return

        val currentVacationUser = users.find { it.role == Role.VACATION }
        currentVacationUser?.role = Role.NORMAL
        currentVacationUser?.let { userRepository.save(it) }

        val currentIndex = users.indexOf(currentVacationUser)
        val nextIndex = if (currentIndex == -1 || currentIndex == users.size - 1) 0 else currentIndex + 1
        val newVacationUser = users[nextIndex]
        newVacationUser.role = Role.VACATION
        userRepository.save(newVacationUser)
    }

    fun getAllUsers(): List<UserDTO> = userRepository.findAll().map(userMapper::toDTO)

    fun getUserById(id: Long): UserDTO {
        val user = findUserById(id)
        return userMapper.toDTO(user)
    }

    fun createUser(createUserDTO: CreateUserDTO): UserDTO {
        if (userRepository.findByUsername(createUserDTO.username) != null) {
            throw IllegalArgumentException("Username already exists")
        }
        if (findUserByRole(Role.VACATION).isNotEmpty() && createUserDTO.role == Role.VACATION) {
            throw IllegalArgumentException("There is already a user on vacation")
        }
        val user = userMapper.fromCreateDTO(createUserDTO)
        user.password = bCryptPasswordEncoder.encode(user.password)
        val savedUser = userRepository.save(user)
        return userMapper.toDTO(savedUser)
    }

    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }

    fun findUserByRole(role: Role): List<UserDTO> {
        return userRepository.findByRole(role).map(userMapper::toDTO)
    }

    internal fun findUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow { UserNotFoundException("User not found with id: $id") }
    }

    internal fun getAllAvailableUsers(): List<User> {
        return userRepository.findByRole(Role.NORMAL)
    }


}
