package com.filemanagement.filemanagement.dto

import com.filemanagement.filemanagement.model.Role
import com.filemanagement.filemanagement.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun toDTO(user: User): UserDTO {
        return UserDTO(
            id = user.id,
            username = user.username,
            role = user.role
        )
    }

    fun fromCreateDTO(dto: CreateUserDTO): User {
        return User(
            username = dto.username,
            password = dto.password,
            role = dto.role ?: Role.NORMAL
        )
    }

    fun fromUpdateDTO(dto: UpdateUserDTO, user: User): User {
        return user.copy(
            username = dto.username ?: user.username,
            password = dto.password ?: user.password,
            role = dto.role ?: user.role
        )
    }
}
