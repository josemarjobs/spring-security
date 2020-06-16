package com.example.springsecurityjwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import sun.plugin.liveconnect.SecurityContextHelper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
        private val userDetailsService: MyUserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val authHeader = req.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(req, res)
            return
        }

        val jwt = authHeader.substring(7)
        val username = JwtUtil.extractUsername(jwt)

        if (SecurityContextHolder.getContext().authentication != null) {
            chain.doFilter(req, res)
            return
        }

        val userDetails = userDetailsService.loadUserByUsername(username)
        if (!JwtUtil.validateToken(jwt, userDetails)) {
            chain.doFilter(req, res)
            return
        }

        val auth = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        auth.details = WebAuthenticationDetailsSource().buildDetails(req);
        SecurityContextHolder.getContext().authentication = auth

        chain.doFilter(req, res)
    }
}