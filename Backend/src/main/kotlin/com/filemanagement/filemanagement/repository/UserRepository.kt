package com.filemanagement.filemanagement.repository

import com.filemanagement.filemanagement.model.Role
import com.filemanagement.filemanagement.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByRole(role: Role): List<User>
    fun findByUsername(username: String): User?

}