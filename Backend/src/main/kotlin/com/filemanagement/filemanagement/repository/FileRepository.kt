package com.filemanagement.filemanagement.repository

import com.filemanagement.filemanagement.model.File
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FileRepository : JpaRepository<File, Long> {
    fun findByUserId(userId: Long): List<File>
    fun findByFilename(filename: String): File?

    @Query("SELECT f.user.id, COUNT(f) FROM File f GROUP BY f.user.id ORDER BY COUNT(f) ASC")
    fun countFilesByUser(): List<Array<Any>>

    @Query("SELECT f FROM File f WHERE f.filename = :filename AND f.sender.sendername = :sendername")
    fun findByFilenameAndSendername(@Param("filename") filename: String, @Param("sendername") sendername: String): File?
}



