package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.AuthDTO
import com.filemanagement.filemanagement.security.CustomUserDetails
import com.filemanagement.filemanagement.service.JwtService
import com.filemanagement.filemanagement.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    @Autowired private val authenticationManager: AuthenticationManager,
    @Autowired private val userService: UserService,
    @Autowired private val jwtService: JwtService
) {

    @PostMapping("/login")
    fun loginUser(@RequestBody authDTO: AuthDTO): ResponseEntity<String> {
        val authenticationToken = UsernamePasswordAuthenticationToken(authDTO.username, authDTO.password)
        authenticationManager.authenticate(authenticationToken)

        val userDetails = userService.loadUserByUsername(authDTO.username) as CustomUserDetails
        val jwtToken = jwtService.generateToken(userDetails.username)

        return ResponseEntity.ok(jwtToken)
    }
}
