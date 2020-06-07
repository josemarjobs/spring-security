package com.example.securitydatajpa

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var username: String? = null,
        var password: String? = null,
        var roles: String? = null
)

class MyUserDetails(private val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles!!.split(",").map {
            SimpleGrantedAuthority(it)
        }.toMutableList()
    }

    override fun getUsername(): String = user.username!!

    override fun getPassword(): String = user.password!!

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}

