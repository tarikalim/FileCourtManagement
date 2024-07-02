package com.filemanagement.filemanagement.dto.user

import com.filemanagement.filemanagement.model.Role
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.Pattern


data class CreateUserDTO(
    @field:NotBlank
    @field:Size(min = 3, max = 50)
    @field:Pattern(
        regexp = "^\\d+\\. Ağır Ceza Mahkemesi$",
        message = "Username must be in the format of 'X. Ağır Ceza Mahkemesi' where X is a number."

    )
    val username: String,
    @field:NotBlank
    @field:Size(min = 6, max = 50)
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}\$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    val password: String,

    val role: Role? = null
)

