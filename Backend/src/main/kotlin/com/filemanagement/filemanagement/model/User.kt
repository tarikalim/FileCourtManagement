package com.filemanagement.filemanagement.model

import jakarta.persistence.*
@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var role: Role,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val files: List<File> = mutableListOf()
)

enum class Role(val roleName: String) {
    NORMAL("ROLE_NORMAL"),
    VACATION("ROLE_VACATION");
}

