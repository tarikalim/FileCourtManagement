package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.notification.CreateNotificationDTO
import com.filemanagement.filemanagement.dto.notification.NotificationDTO
import com.filemanagement.filemanagement.security.CustomUserDetails
import com.filemanagement.filemanagement.service.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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

    @GetMapping("/me")
    fun getCurrentUserNotifications(@AuthenticationPrincipal userDetails: CustomUserDetails): ResponseEntity<List<NotificationDTO>> {
        val notifications = notificationService.getNotificationsByUsername(userDetails.username)
        return ResponseEntity(notifications, HttpStatus.OK)
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

    @PutMapping("/{id}")
    @PreAuthorize("@notificationService.isOwner(#id, #userDetails.username)")
    fun updateNotification(
        @PathVariable id: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ResponseEntity<NotificationDTO> {
        val updatedNotification = notificationService.markNotificationAsRead(id)
        return ResponseEntity(updatedNotification, HttpStatus.OK)
    }


}