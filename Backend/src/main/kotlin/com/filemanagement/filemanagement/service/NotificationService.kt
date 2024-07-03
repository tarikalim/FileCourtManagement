package com.filemanagement.filemanagement.service

import com.filemanagement.filemanagement.dto.notification.CreateNotificationDTO
import com.filemanagement.filemanagement.dto.notification.NotificationDTO
import com.filemanagement.filemanagement.exception.NotificationNotFoundException
import com.filemanagement.filemanagement.dto.notification.NotificationMapper
import com.filemanagement.filemanagement.model.Notification
import com.filemanagement.filemanagement.repository.NotificationRepository
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val userService: UserService,
    private val notificationMapper: NotificationMapper
) {

    internal fun findNotificationById(id: Long): Notification {
        return notificationRepository.findById(id)
            .orElseThrow { NotificationNotFoundException("Notification not found with id $id") }
    }

    fun getAllNotifications() = notificationRepository.findAll().map(notificationMapper::toDTO)


    fun getNotificationById(id: Long): NotificationDTO {
        val notification = findNotificationById(id)
        return notificationMapper.toDTO(notification)

    }

    fun getNotificationsByUsername(username: String): List<NotificationDTO> {
        userService.findUserByUsername(username)
        return notificationRepository.findByReceiver_Username(username).map { notificationMapper.toDTO(it) }
    }

    fun createNotification(senderName: String, createNotificationDTO: CreateNotificationDTO): NotificationDTO {
        val sender = userService.findUserByUsername(senderName)
        val receiver = userService.findUserByUsername(createNotificationDTO.receiverName)
        val notification = notificationMapper.toEntity(createNotificationDTO, sender, receiver)
        val savedNotification = notificationRepository.save(notification)
        return notificationMapper.toDTO(savedNotification)
    }

    fun markNotificationAsRead(notificationId: Long): NotificationDTO {
        val notification = findNotificationById(notificationId)
        notification.isRead = true
        notificationRepository.save(notification)
        return notificationMapper.toDTO(notification)

    }
// function to check whoever making a change over notification is the owner of that notification
    fun isOwner(notificationId: Long, username: String): Boolean {
        val notification = findNotificationById(notificationId)
        return notification.receiver.username == username
    }

}