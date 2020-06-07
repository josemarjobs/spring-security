package com.example.securityjdbc

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeResource {

    @GetMapping("/")
    fun home() = "<h1>Welcome</h1"

    @GetMapping("/user")
    fun user() = "<h1>Welcome User</h1"

    @GetMapping("/admin")
    fun admin() = "<h1>Welcome Admin</h1"
}