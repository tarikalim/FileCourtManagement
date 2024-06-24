package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.CreateUserDTO
import com.filemanagement.filemanagement.dto.FileDTO
import com.filemanagement.filemanagement.dto.UserDTO
import com.filemanagement.filemanagement.security.CustomUserDetails
import com.filemanagement.filemanagement.service.FileService
import com.filemanagement.filemanagement.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val fileService: FileService,
) {

    @GetMapping
    fun getAllUsers(): List<UserDTO> = userService.getAllUsers()


    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        val user = userService.getUserById(id)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @GetMapping("/me")
    fun getCurrentUser(@AuthenticationPrincipal userDetails: CustomUserDetails): ResponseEntity<UserDTO> {
        val user = userService.getUserById(userDetails.id)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @GetMapping("/{id}/files")
    fun getFilesByUserId(@PathVariable id: Long): ResponseEntity<List<FileDTO>> {
        val files = fileService.getFilesByUserId(id)
        return ResponseEntity(files, HttpStatus.OK)
    }
    @GetMapping("/files/search")
    fun getFilesByUserUsername(@RequestParam username: String): ResponseEntity<List<FileDTO>> {
        val files = fileService.getFilesByUserUsername(username)
        return ResponseEntity(files, HttpStatus.OK)
    }

    @GetMapping("/me/files")
    fun getFilesByCurrentUser(@AuthenticationPrincipal userDetails: CustomUserDetails): ResponseEntity<List<FileDTO>> {
        val files = fileService.getFilesByUserId(userDetails.id)
        return ResponseEntity(files, HttpStatus.OK)
    }


    @PostMapping
    fun createUser(@Valid @RequestBody createUserDTO: CreateUserDTO): ResponseEntity<UserDTO> {
        val user = userService.createUser(createUserDTO)
        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }


}





