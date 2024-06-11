package com.filemanagement.filemanagement.service

import com.filemanagement.filemanagement.dto.CreateUserDTO
import com.filemanagement.filemanagement.dto.UserDTO
import com.filemanagement.filemanagement.dto.UserMapper
import com.filemanagement.filemanagement.exception.UserNotFoundException
import com.filemanagement.filemanagement.model.Role
import com.filemanagement.filemanagement.repository.UserRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {

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
        val user = userRepository.findById(id).orElseThrow { UserNotFoundException("User not found with id: $id") }
        return userMapper.toDTO(user)
    }


    fun createUser(createUserDTO: CreateUserDTO): UserDTO {
        if (userRepository.findByUsername(createUserDTO.username) != null ) {
            throw IllegalArgumentException("Username already exists")
        }
        if (userRepository.findByRole(Role.VACATION).isNotEmpty()) {
            throw IllegalArgumentException("There is already a user on vacation")
        }
        val user = userMapper.fromCreateDTO(createUserDTO)
        val savedUser = userRepository.save(user)
        return userMapper.toDTO(savedUser)
    }

    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }
}
