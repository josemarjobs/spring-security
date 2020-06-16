package com.example.springsecurityjwt

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(p0: String?): UserDetails {
        return User("peterg", "secret", mutableListOf())
    }
}