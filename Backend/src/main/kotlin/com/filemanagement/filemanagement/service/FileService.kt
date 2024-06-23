package com.filemanagement.filemanagement.service

import com.filemanagement.filemanagement.dto.AddFileDTO
import com.filemanagement.filemanagement.dto.FileDTO
import com.filemanagement.filemanagement.dto.FileMapper
import com.filemanagement.filemanagement.dto.UserFileDTO
import com.filemanagement.filemanagement.repository.FileRepository
import org.springframework.stereotype.Service
import com.filemanagement.filemanagement.exception.FileNotFoundException
import com.filemanagement.filemanagement.model.File

@Service
class FileService(
    private val fileRepository: FileRepository,
    private val fileMapper: FileMapper,
    private val userService: UserService
) {
    fun getAllFiles() = fileRepository.findAll().map(fileMapper::toDTO)
    internal fun findFileById(id: Long): File {
        return fileRepository.findById(id).orElseThrow { FileNotFoundException("File not found with id: $id") }
    }

    fun getFileById(id: Long): UserFileDTO {
        val file = findFileById(id)
        return fileMapper.toUserFileDTO(file)
    }

    fun getFilesByUserId(userId: Long): List<FileDTO> {
        val user = userService.findUserById(userId)
        return fileRepository.findByUserId(user.id).map(fileMapper::toBasicDTO)
    }


    fun deleteFile(id: Long) {
        if (!fileRepository.existsById(id)) {
            throw FileNotFoundException("File not found with id: $id")
        }
        fileRepository.deleteById(id)
    }

    fun createFile(addFileDTO: AddFileDTO): UserFileDTO {
        if (fileRepository.findByFilename(addFileDTO.filename) != null) {
            throw IllegalArgumentException("This file already in the system: ${addFileDTO.filename}")
        }
        val availableUsers = userService.getAllAvailableUsers()
        val fileCounts = fileRepository.countFilesByUser()
        val userFileCounts = fileCounts.associate { it[0] as Long to it[1] as Long }
        val userWithLeastFiles = availableUsers.minByOrNull { userFileCounts[it.id] ?: 0L }
            ?: throw IllegalStateException("No users available")
        val file = fileMapper.fromAddDTO(addFileDTO, userWithLeastFiles)
        val savedFile = fileRepository.save(file)
        return fileMapper.toUserFileDTO(savedFile)

    }

    fun findFileByFilename(filename: String): UserFileDTO {
        val file = fileRepository.findByFilename(filename)
            ?: throw FileNotFoundException("File not found with filename: $filename")
        return fileMapper.toUserFileDTO(file)
    }


}



