package com.filemanagement.filemanagement.controller

import com.filemanagement.filemanagement.dto.AuthDTO
import com.filemanagement.filemanagement.security.CustomUserDetails
import com.filemanagement.filemanagement.service.JwtService
import com.filemanagement.filemanagement.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val userService: UserService,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager,

) {
    @PostMapping("/login")
    fun loginUser(@RequestBody authDTO: AuthDTO): String {
        val authenticationToken = UsernamePasswordAuthenticationToken(authDTO.username, authDTO.password)
        val authentication: Authentication = authenticationManager.authenticate(authenticationToken)

        if (authentication.isAuthenticated) {
            val userDetails = userService.loadUserByUsername(authDTO.username) as CustomUserDetails
            return jwtService.generateToken(userDetails.username, userDetails.id.toString())
        } else {
            throw UsernameNotFoundException("Invalid username or password: ${authDTO.username}")

        }
    }


}