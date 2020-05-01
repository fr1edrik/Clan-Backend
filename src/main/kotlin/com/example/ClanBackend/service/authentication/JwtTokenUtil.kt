package com.example.ClanBackend.service.authentication

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtTokenUtil : Serializable {
    @Value("{jwt.sectret}")
    private val secret: String = ""

    // retrieve username from jwt token
    fun getUsernameFromToken(token: String): String{
        return ""
    }

    //retrieve expiration date from jwt token
    fun getExpirationDateFromToken(token: String): Date{
        return Date()
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: (Claims, T)->T): (Claims, T) -> T
    {
        val claims: Claims = getAllClaimsFromToken(token)
        return claimsResolver.apply { claims }
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean
    {
        val expiration: Date = getExpirationDateFromToken(token)
        return  expiration.before(Date())
    }

    fun generateToken(userDetails: UserDetails): String{
        val claims: Map<String, Object> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Object>, subject: String): String
    {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY *1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username: String = getUsernameFromToken(token);
        return (username == userDetails.username && !isTokenExpired(token))
    }

   companion object {
       private const val serialVersionUID: Long =  -2550185165626007488L

       private const val JWT_TOKEN_VALIDITY: Long = 5*60 * 60
   }
}