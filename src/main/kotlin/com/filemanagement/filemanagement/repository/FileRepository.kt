package com.filemanagement.filemanagement.repository

import com.filemanagement.filemanagement.model.File
import com.filemanagement.filemanagement.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FileRepository : JpaRepository<File, Long> {
    fun findByUser(user: User): List<File>
    fun countByUserId(userId: Long): Long
    fun findByFilename(filename: String): List<File>

    @Query("SELECT f.user.id, COUNT(f) FROM File f GROUP BY f.user.id ORDER BY COUNT(f) ASC")
    fun countFilesByUser(): List<Array<Any>>
}



