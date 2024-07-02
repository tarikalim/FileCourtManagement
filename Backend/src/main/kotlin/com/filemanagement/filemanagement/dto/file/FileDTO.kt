package com.filemanagement.filemanagement.dto.file

data class FileDTO(
    val id: Long,
    val filename: String,
    val sendername: String,
    val assignedUser: String
)
