package com.niderlandi.domodedovo.refresh

import com.niderlandi.domodedovo.auth.User
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@EnableJpaRepositories
@Repository("refreshTokenRepository")
interface RefreshTokenRepository: CrudRepository<RefreshToken, Long> {
    fun findRefreshTokenByUser(user : User) : RefreshToken?
}