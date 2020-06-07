package com.example.securitydatajpa

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCrypt

@SpringBootApplication
class SecurityDataJpaApplication {

    @Bean
    fun runner(userRepository: UserRepository) = CommandLineRunner {
        userRepository.save(User(
                username = "peterg",
                password = BCrypt.hashpw("pass", BCrypt.gensalt()),
                roles = "ROLE_USER"
        ))
        userRepository.save(User(
                username = "loisg",
                password = BCrypt.hashpw("pass", BCrypt.gensalt()),
                roles = "ROLE_USER,ROLE_ADMIN"
        ))
    }
}

fun main(args: Array<String>) {
    runApplication<SecurityDataJpaApplication>(*args)
}
