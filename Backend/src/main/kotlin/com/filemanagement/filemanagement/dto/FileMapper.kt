package com.filemanagement.filemanagement.dto

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

    fun toUserFileDTO(file: File): UserFileDTO {
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

    // AddFileDTO to File entity for createFile method
    fun fromAddDTO(dto: AddFileDTO, user: User): File {
        return File(
            filename = dto.filename,
            user = user
        )
    }
    fun toBasicDTO(file: File): FileDTO {
        return FileDTO(
            id = file.id,
            filename = file.filename
        )
    }

    // File entity to AddFileDTO for createFile method response
    fun toAddDTO(file: File): AddFileDTO {
        return AddFileDTO(
            filename = file.filename
        )
    }
}
