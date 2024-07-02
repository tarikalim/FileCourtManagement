package com.filemanagement.filemanagement.dto.notification

import com.filemanagement.filemanagement.model.NotificationType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateNotificationDTO(
    @field:NotBlank
    val receiverName: String,
    @field:NotBlank
    val content: String,
    @field:NotNull
    val type: NotificationType
)
