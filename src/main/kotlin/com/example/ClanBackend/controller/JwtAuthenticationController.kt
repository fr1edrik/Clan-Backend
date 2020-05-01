package com.example.ClanBackend.controller

import com.example.ClanBackend.model.authentication.JwtRequest
import com.example.ClanBackend.model.authentication.JwtResponse
import com.example.ClanBackend.service.authentication.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import kotlin.Exception

@RestController
@CrossOrigin
abstract class JwtAuthenticationController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<Any> {
        authenticate(authenticationRequest.username?:"", authenticationRequest.password?:"")
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)

        val token: String = jwtTokenUtil.generateToken(userDetails)

        return ResponseEntity.ok(JwtResponse(token))
    }

    private fun authenticate(username: String, password: String)
    {
        try {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password));
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS")
        }
    }

}