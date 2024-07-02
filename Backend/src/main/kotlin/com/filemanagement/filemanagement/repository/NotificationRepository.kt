package com.filemanagement.filemanagement.repository

import com.filemanagement.filemanagement.model.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByReceiver_Username(receiverUsername: String): List<Notification>
}
