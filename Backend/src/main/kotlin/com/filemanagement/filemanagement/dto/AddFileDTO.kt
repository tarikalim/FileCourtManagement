package com.filemanagement.filemanagement.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AddFileDTO(
    @field:NotBlank
    @field:Pattern(
        regexp = "\\d{4}/\\d+",
        message = "Filename must be in the format YYYY/N, where YYYY is the year and N is a number"
    )
    val filename: String,
)
