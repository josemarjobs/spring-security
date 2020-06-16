package com.example.springsecurityjwt

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloResource(
        private val authManager: AuthenticationManager,
        private val userDetailsService: MyUserDetailsService
) {

    @GetMapping("/")
    fun hello() = "<h1>Hello World</h1>"

    @PostMapping("/authenticate")
    fun authenticate(
            @RequestBody req: AuthenticationRequest
    ): ResponseEntity<*> {
        try {
            val auth = UsernamePasswordAuthenticationToken(req.username, req.password)
            authManager.authenticate(auth)

            val userDetails = userDetailsService.loadUserByUsername(req.username)
            val token = JwtUtil.generateToken(userDetails)

            return ResponseEntity.ok(AuthenticationResponse(token))
        } catch (e: BadCredentialsException) {
            return ResponseEntity.badRequest().body(mapOf("error" to "Bad credentials"))
        }
    }
}