package com.filemanagement.filemanagement.service

import com.filemanagement.filemanagement.dto.sender.AddSenderDTO
import com.filemanagement.filemanagement.dto.sender.SenderDTO
import com.filemanagement.filemanagement.dto.sender.SenderMapper
import com.filemanagement.filemanagement.exception.SenderNotFoundException
import com.filemanagement.filemanagement.model.Sender
import com.filemanagement.filemanagement.repository.SenderRepository
import org.springframework.stereotype.Service

@Service
class SenderService(
    private val senderRepository: SenderRepository,
    private val senderMapper: SenderMapper
) {
    internal fun findBySendername(sendername: String): Sender {
        return senderRepository.findBySendername(sendername)
            ?: throw SenderNotFoundException("Sender not found with name: $sendername")
    }

    fun createSender(addSenderDTO: AddSenderDTO): SenderDTO {
        if (senderRepository.findBySendername(addSenderDTO.sendername) != null) {
            throw IllegalArgumentException("Sender already exists: ${addSenderDTO.sendername}")
        }
        val sender = senderMapper.fromDTO(addSenderDTO)
        val savedSender = senderRepository.save(sender)
        return senderMapper.toDTO(savedSender)
    }

    fun getAllSenders(): List<SenderDTO> {
        return senderRepository.findAll().map { senderMapper.toDTO(it) }
    }

}