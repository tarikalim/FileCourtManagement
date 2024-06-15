package com.filemanagement.filemanagement.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtService {
    @Value("\${secret.key}")
    private lateinit var secret: String

    fun generateToken(userName: String, userId: String): String {
        val claims: MutableMap<String, Any> = HashMap()
        claims["userId"] = userId
        return createToken(claims, userName)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUser(token)
        val expirationDate = extractExpiration(token)
        return userDetails.username == username && !expirationDate.before(Date())
    }

    private fun extractExpiration(token: String): Date {
        val claims = Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body
        return claims.expiration
    }

    fun extractUser(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    fun extractUserId(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body
        return claims["userId"] as String
    }

    private fun createToken(claims: Map<String, Any>, userName: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 15))
            .signWith(getSignKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getSignKey(): Key {
        val keyBytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
