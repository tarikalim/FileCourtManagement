package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.CreateUserDTO
import com.filemanagement.filemanagement.dto.UserDTO
import com.filemanagement.filemanagement.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): List<UserDTO> = userService.getAllUsers()


    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(@RequestBody createUserDTO: CreateUserDTO): ResponseEntity<UserDTO> {
        val user = userService.createUser(createUserDTO)
        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

}





