package com.niderlandi.domodedovo.refresh

import com.niderlandi.domodedovo.auth.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class RefreshTokenService {
    private val logger: Logger = LoggerFactory.getLogger(RefreshTokenService::class.java)

    @Qualifier("refreshTokenRepository")
    @Autowired
    private val refreshTokenRepository: RefreshTokenRepository? = null

    @Transactional
    fun createByUser(user: User, fingerprint: String): RefreshToken {
        // Если существует:
        val refreshToken = RefreshToken(user, fingerprint)

        val refreshTokenRep = refreshTokenRepository?.findRefreshTokenByUser(user)
        if (refreshTokenRep != null) {
            logger.debug("Токен $refreshTokenRep удалён для $user")
            refreshTokenRepository?.delete(refreshTokenRep)
        }

        refreshTokenRepository?.save(refreshToken)

        logger.debug("Для $user был создан $refreshToken")
        return refreshToken
    }

    fun isValid(user: User, token: UUID, fingerprint: String): Boolean {
        val refreshToken = refreshTokenRepository?.findRefreshTokenByUser(user) ?: return false

        if (refreshToken.uuid == token &&
            refreshToken.expiresDate.after(Date()) &&
            refreshToken.fingerprint == fingerprint
        ) {
            logger.debug("Refresh token $token для $user корректный.")
            return true
        }

        logger.debug("Refresh token $token для $user неверный.")
        return false
    }
}