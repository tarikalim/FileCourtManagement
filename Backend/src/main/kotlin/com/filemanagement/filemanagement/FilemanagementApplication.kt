package com.filemanagement.filemanagement

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class FilemanagementApplication

fun main(args: Array<String>) {
	runApplication<FilemanagementApplication>(*args)
}
