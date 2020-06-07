package com.example.securitydatajpa

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeResource {

    @GetMapping("/")
    fun home() = "<h1>Welcome</h1>"

    @GetMapping("/user")
    fun user() = "<h1>Welcome USER</h1>"

    @GetMapping("/admin")
    fun admin() = "<h1>Welcome ADMIN</h1>"
}