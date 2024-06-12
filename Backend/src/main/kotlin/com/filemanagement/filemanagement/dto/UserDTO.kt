package com.filemanagement.filemanagement.dto

import com.filemanagement.filemanagement.model.Role

data class UserDTO(
    val id: Long,
    val username: String,
    val role: Role
)
