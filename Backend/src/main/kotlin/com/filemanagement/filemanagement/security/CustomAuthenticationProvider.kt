package com.filemanagement.filemanagement.security

import com.filemanagement.filemanagement.service.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val userService: UserService,
    private val passwordEncoder: BCryptPasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        val userDetails = userService.loadUserByUsername(username)

        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw BadCredentialsException("Invalid username or password")
        }

        return UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
