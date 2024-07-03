package com.filemanagement.filemanagement.service

import com.filemanagement.filemanagement.dto.file.AddFileDTO
import com.filemanagement.filemanagement.dto.file.FileDTO
import com.filemanagement.filemanagement.dto.file.FileMapper
import com.filemanagement.filemanagement.repository.FileRepository
import org.springframework.stereotype.Service
import com.filemanagement.filemanagement.exception.FileNotFoundException
import com.filemanagement.filemanagement.model.File

@Service
class FileService(
    private val fileRepository: FileRepository,
    private val fileMapper: FileMapper,
    private val userService: UserService,
    private val senderService: SenderService
) {
    fun getAllFiles() = fileRepository.findAll().map(fileMapper::toDTO)
    internal fun findFileById(id: Long): File {
        return fileRepository.findById(id).orElseThrow { FileNotFoundException("File not found with id: $id") }
    }

    fun getFileById(id: Long): FileDTO {
        val file = findFileById(id)
        return fileMapper.toDTO(file)
    }

    fun getFilesByUserId(userId: Long): List<FileDTO> {
        val user = userService.findUserById(userId)
        return fileRepository.findByUserId(user.id).map(fileMapper::toDTO)
    }

    fun getFilesByUserUsername(username: String): List<FileDTO> {
        val user = userService.findUserByUsername(username)
        return getFilesByUserId(user.id)
    }


    fun deleteFile(id: Long) {
        if (!fileRepository.existsById(id)) {
            throw FileNotFoundException("File not found with id: $id")
        }
        fileRepository.deleteById(id)
    }

    fun createFile(addFileDTO: AddFileDTO): FileDTO {
        if (fileRepository.findByFilenameAndSender_Sendername(addFileDTO.filename, addFileDTO.sendername) != null) {
            throw IllegalArgumentException("This file already exists: ${addFileDTO.filename}")
        }
        val availableUsers = userService.getAllAvailableUsers()
        val fileCounts = fileRepository.countFilesByUser()
        val userFileCounts = fileCounts.associate { it[0] as Long to it[1] as Long }
        val userWithLeastFiles = availableUsers.minByOrNull { userFileCounts[it.id] ?: 0L }
            ?: throw IllegalStateException("No users available")
        val sender = senderService.findBySendername(addFileDTO.sendername)
        val file = fileMapper.toEntity(addFileDTO, userWithLeastFiles, sender)
        val savedFile = fileRepository.save(file)
        return fileMapper.toDTO(savedFile)

    }

    fun findFileByFilenameAndSendername(filename: String, sendername: String): FileDTO {
        val file = fileRepository.findByFilenameAndSender_Sendername(filename, sendername)
            ?: throw FileNotFoundException("File not found with filename: $filename and sendername: $sendername")
        return fileMapper.toDTO(file)
    }


}



