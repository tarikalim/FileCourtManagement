package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.file.AddFileDTO
import com.filemanagement.filemanagement.dto.file.FileDTO
import com.filemanagement.filemanagement.service.FileService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/files")

class FileController(private val fileService: FileService) {

    @GetMapping
    fun getAllFiles() = fileService.getAllFiles()

    @GetMapping("/{id}")
    fun getFileById(@PathVariable id: Long): ResponseEntity<FileDTO> {
        val file = fileService.getFileById(id)
        return ResponseEntity(file, HttpStatus.OK)
    }

    @GetMapping("/search")
    fun findFileByFilenameAndSendername(
        @RequestParam filename: String,
        @RequestParam sendername: String
    ): ResponseEntity<FileDTO> {
        val file = fileService.findFileByFilenameAndSendername(filename, sendername)
        return ResponseEntity(file, HttpStatus.OK)
    }


    @PostMapping
    fun createFile(@Valid @RequestBody addFileDTO: AddFileDTO): ResponseEntity<FileDTO> {
        val file = fileService.createFile(addFileDTO)
        return ResponseEntity(file, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteFile(@PathVariable id: Long): ResponseEntity<Unit> {
        fileService.deleteFile(id)
        return ResponseEntity.noContent().build()
    }


}