package com.filemanagement.filemanagement.model

import jakarta.persistence.*

@Entity
data class Sender (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val sendername: String,

    @OneToMany(mappedBy = "sender", cascade = [CascadeType.ALL], orphanRemoval = true)
    val files: List<File> = mutableListOf()
)
