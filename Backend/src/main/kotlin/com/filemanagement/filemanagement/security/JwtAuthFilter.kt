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
import org.springframework.web.servlet.HandlerExceptionResolver
import java.io.IOException
import java.util.Optional

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userService: UserService,
    private val handlerExceptionResolver: HandlerExceptionResolver
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        try {
            val token = Optional.ofNullable(authHeader)
                .filter { it.startsWith("Bearer ") }
                .map { it.substring(7) }

            token.ifPresent {
                val userName = jwtService.extractUser(it)

                if (SecurityContextHolder.getContext().authentication == null) {
                    val userDetails: UserDetails = userService.loadUserByUsername(userName)

                    if (jwtService.validateToken(it, userDetails)) {
                        val authToken = UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.authorities
                        )
                        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                }
            }

            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            handlerExceptionResolver.resolveException(request, response, null, e)
        }
    }
}
