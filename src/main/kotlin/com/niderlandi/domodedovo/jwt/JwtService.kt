package com.niderlandi.domodedovo.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.auth.User
import com.niderlandi.domodedovo.refresh.RefreshTokenService
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.*

@Service
class JwtService{
    private val logger: Logger = LoggerFactory.getLogger(JwtService::class.java)

    @Autowired
    val refreshTokenService: RefreshTokenService? = null

    companion object {
        /**
         * Подпись для токена.
         */
        private const val ISSUER = "Domodedovo"
        private const val secretKey: String = "dasda"
        private const val minutes : Long = 120
    }

    fun createAccessToken(user: User): String {
        return Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()), SignatureAlgorithm.HS512)
            .setSubject(user.telephone)
            .setIssuer(ISSUER)
            .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(minutes))))
            .setIssuedAt(Date.from(Instant.now()))
            .compact()
    }

    fun validateToken(token: String): JwtResponseWrapper {
        try {
            val claim = parseAccessToken(token)

            if (claim.body?.subject == null)
                return getIncorrectTokenResponse()
        } catch (e: ExpiredJwtException) {
            logger.debug("Срок действие токена $token истекло.")
            return JwtResponseWrapper(HttpStatus.FORBIDDEN, "The token has expired", null)
        } catch (e: JwtException) {
            return getIncorrectTokenResponse()
        }

        logger.debug("Токен $token успешно прошёл валидацию.")
        return JwtResponseWrapper(
            HttpStatus.OK,
            "Correct token",
            parseAccessToken(token).body?.subject ?: return getIncorrectTokenResponse()
        )
    }

    private fun getIncorrectTokenResponse(): JwtResponseWrapper {
        return JwtResponseWrapper(HttpStatus.UNAUTHORIZED, "Incorrect token", null)
    }


    fun parseAccessToken(token: String): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseClaimsJws(token)
    }


    fun getJsonTokens(user: User, fingerPrint: String): String {
        val accessToken = createAccessToken(user)
        val refreshToken = refreshTokenService?.createByUser(user, fingerPrint)?.uuid

        logger.debug("Для $user получены access и refresh токены.")

        return jacksonObjectMapper().writeValueAsString(
            mapOf<String, Any?>(
                "accessToken" to accessToken,
                "refreshToken" to refreshToken
            )
        )
    }
}