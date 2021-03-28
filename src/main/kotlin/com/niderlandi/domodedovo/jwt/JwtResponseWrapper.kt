package com.niderlandi.domodedovo.jwt

import org.springframework.http.HttpStatus


data class JwtResponseWrapper(val httpStatus: HttpStatus, val message: String, val telephone: String?)