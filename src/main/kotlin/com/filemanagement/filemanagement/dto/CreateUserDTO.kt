package com.filemanagement.filemanagement.dto
import com.filemanagement.filemanagement.model.Role

data class CreateUserDTO(
    val username: String,
    val password: String,
    val role: Role? = null
)

