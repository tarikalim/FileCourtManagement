package com.filemanagement.filemanagement.security

import com.filemanagement.filemanagement.service.JwtService
import com.filemanagement.filemanagement.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userService: UserService
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader = request.getHeader("Authorization")
        var token: String? = null
        var userName: String? = null

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7)
            userName = jwtService.extractUser(token)
        }

        if (userName != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails = userService.loadUserByUsername(userName)

            if (jwtService.validateToken(token!!, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }
}
