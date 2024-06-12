package com.filemanagement.filemanagement.model

import jakarta.persistence.*
@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    var role: Role
)

enum class Role {
    NORMAL,VACATION
}
