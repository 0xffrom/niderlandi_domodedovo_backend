package com.niderlandi.domodedovo.auth

import org.springframework.stereotype.Service

@Service
class AuthService {

    fun getUserByToken(): User {
        return User()
    }
}