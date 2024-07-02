package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.notification.CreateNotificationDTO
import com.filemanagement.filemanagement.dto.notification.NotificationDTO
import com.filemanagement.filemanagement.security.CustomUserDetails
import com.filemanagement.filemanagement.service.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/notifications")
class NotificationController(
    private var notificationService: NotificationService

) {
    @GetMapping
    fun getAllNotifications(): List<NotificationDTO> = notificationService.getAllNotifications()

    @GetMapping("/{id}")
    fun getNotificationById(@PathVariable id: Long): ResponseEntity<NotificationDTO> {
        val notification = notificationService.getNotificationById(id)
        return ResponseEntity.ok(notification)
    }

    @PostMapping
    fun createNotification(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody createNotificationDTO: CreateNotificationDTO
    ): ResponseEntity<NotificationDTO> {
        val senderName = userDetails.username
        val notification = notificationService.createNotification(senderName, createNotificationDTO)
        return ResponseEntity(notification, HttpStatus.CREATED)
    }

}