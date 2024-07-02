package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.sender.AddSenderDTO
import com.filemanagement.filemanagement.dto.sender.SenderDTO
import com.filemanagement.filemanagement.service.SenderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/senders")
class SenderController(private val senderService: SenderService) {

    @GetMapping
    fun getAllSenders() = senderService.getAllSenders()

    @PostMapping
    fun createSender(@Valid @RequestBody addSenderDTO: AddSenderDTO): ResponseEntity<SenderDTO> {
        val sender = senderService.createSender(addSenderDTO)
        return ResponseEntity(sender, HttpStatus.CREATED)
    }

}