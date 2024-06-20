package com.filemanagement.filemanagement

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class FilemanagementApplication

fun main(args: Array<String>) {
    val dotenv = Dotenv.load()

    dotenv.entries().forEach { entry ->
        System.setProperty(entry.key, entry.value)
    }

    runApplication<FilemanagementApplication>(*args)
}
