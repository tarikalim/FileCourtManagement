package com.filemanagement.filemanagement.dto.notification

import com.filemanagement.filemanagement.model.NotificationType
import java.time.LocalDateTime

data class NotificationDTO(
    val id: Long,
    val senderName: String?,
    val receiverName: String,
    val content: String,
    val type: NotificationType,
    val isRead: Boolean,
    val createdAt: LocalDateTime
)
