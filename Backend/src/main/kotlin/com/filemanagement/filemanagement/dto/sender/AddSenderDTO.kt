package com.filemanagement.filemanagement.dto.sender
import jakarta.validation.constraints.NotBlank

data class AddSenderDTO(
    @NotBlank
    val sendername: String,
)
