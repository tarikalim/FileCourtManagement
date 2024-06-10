package com.filemanagement.filemanagement.repository

import com.filemanagement.filemanagement.model.File
import com.filemanagement.filemanagement.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Long> {
    fun findByUser(user: User): List<File>
}