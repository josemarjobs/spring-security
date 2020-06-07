package com.example.securitybasics

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    @GetMapping("/")
    fun home() = "<h1>Hello World</h1>"

    @GetMapping("/user")
    fun user() = "<h1>Welcome user</h1>"

    @GetMapping("/admin")
    fun admin() = "<h1>Welcome admin</h1>"
}