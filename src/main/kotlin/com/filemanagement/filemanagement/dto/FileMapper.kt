package com.filemanagement.filemanagement.dto

import com.filemanagement.filemanagement.dto.UserFileDTO
import com.filemanagement.filemanagement.dto.AddFileDTO
import com.filemanagement.filemanagement.model.File
import com.filemanagement.filemanagement.model.User
import org.springframework.stereotype.Component

@Component
class FileMapper {

    // Entity to DTO
    fun toDTO(file: File): UserFileDTO {
        return UserFileDTO(
            id = file.id,
            filename = file.filename,
            assignedUser = file.user.username
        )
    }

    // DTO to Entity
    fun fromDTO(dto: AddFileDTO, user: User): File {
        return File(
            filename = dto.filename,
            user = user
        )
    }
}
