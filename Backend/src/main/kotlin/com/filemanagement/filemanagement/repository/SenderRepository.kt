package com.filemanagement.filemanagement.repository

import com.filemanagement.filemanagement.model.Sender
import org.springframework.data.jpa.repository.JpaRepository

interface SenderRepository : JpaRepository<Sender, Long> {
    fun findBySendername(sendername: String): Sender?
}
