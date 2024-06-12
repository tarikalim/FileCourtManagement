package com.filemanagement.filemanagement.dto

import com.filemanagement.filemanagement.model.Role

data class UpdateUserDTO(
    val username: String?,
    val password: String?,
    val role: Role?
)
