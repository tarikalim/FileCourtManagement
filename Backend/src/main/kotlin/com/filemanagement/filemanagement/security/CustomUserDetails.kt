package com.filemanagement.filemanagement.security

import com.filemanagement.filemanagement.model.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val id: Long,
    private val username: String,
    private val password: String,
    private val role: Role
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role.roleName))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }


}
