package com.filemanagement.filemanagement.model

import jakarta.persistence.*

@Entity
@Table(name = "notification")
data class Notification(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_name", referencedColumnName = "username")
    val sender: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_name", referencedColumnName = "username")
    val receiver: User,

    val content: String,

    var isRead: Boolean = false,
    @Enumerated(EnumType.STRING)
    val type: NotificationType,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: java.time.LocalDateTime = java.time.LocalDateTime.now()
)

enum class NotificationType {
    NEW_FILE_ASSIGNED,
    CUSTOM_CONTENT
}
