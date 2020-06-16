package com.example.springsecurityjwt

data class AuthenticationRequest(
        var username: String? = null,
        var password: String? = null
)

data class AuthenticationResponse(
        var jwt: String? = null
)