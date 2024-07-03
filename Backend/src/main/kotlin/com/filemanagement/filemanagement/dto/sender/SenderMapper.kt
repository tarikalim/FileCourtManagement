package com.filemanagement.filemanagement.dto.sender

import com.filemanagement.filemanagement.model.Sender
import org.springframework.stereotype.Component

@Component
class SenderMapper {

    fun toDTO(sender: Sender): SenderDTO {
        return SenderDTO(
            id = sender.id,
            sendername = sender.sendername,
        )
    }

    fun toEntity(dto: AddSenderDTO): Sender {
        return Sender(
            sendername = dto.sendername

        )
    }
}