package com.filemanagement.filemanagement.dto.notification

import com.filemanagement.filemanagement.model.Notification
import com.filemanagement.filemanagement.model.User
import org.springframework.stereotype.Component

@Component
class NotificationMapper {

    fun toEntity(createNotificationDTO: CreateNotificationDTO, sender: User, receiver: User): Notification {
        return Notification(
            sender = sender,
            receiver = receiver,
            content = createNotificationDTO.content,
            type = createNotificationDTO.type
        )
    }

    fun toDTO(notification: Notification): NotificationDTO {
        return NotificationDTO(
            id = notification.id,
            senderName = notification.sender?.username,
            receiverName = notification.receiver.username,
            content = notification.content,
            type = notification.type,
            isRead = notification.isRead,
            createdAt = notification.createdAt
        )
    }
}
