package com.filemanagement.filemanagement.dto.user

import com.filemanagement.filemanagement.model.Role

data class UserDTO(
    val id: Long,
    val username: String,
    val role: Role
)
