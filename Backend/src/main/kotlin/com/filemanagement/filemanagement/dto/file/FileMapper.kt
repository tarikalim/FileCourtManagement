package com.filemanagement.filemanagement.dto.file

import com.filemanagement.filemanagement.model.File
import com.filemanagement.filemanagement.model.User
import com.filemanagement.filemanagement.model.Sender
import org.springframework.stereotype.Component

@Component
class FileMapper {

    fun toDTO(file: File): FileDTO {
        return FileDTO(
            id = file.id,
            filename = file.filename,
            sendername = file.sender.sendername,
            assignedUser = file.user.username

        )
    }

    fun toEntity(dto: AddFileDTO, user: User, sender: Sender): File {
        return File(
            filename = dto.filename,
            user = user,
            sender = sender
        )
    }
}
