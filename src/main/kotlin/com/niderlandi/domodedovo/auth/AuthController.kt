package com.niderlandi.domodedovo.auth

import com.niderlandi.domodedovo.jwt.JwtService
import com.niderlandi.domodedovo.refresh.RefreshTokenService
import com.niderlandi.domodedovo.telephone.TelephoneService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
class AuthController {
    private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)

    @Autowired
    private val telephoneService: TelephoneService? = null

    @Autowired
    private val authService: AuthService? = null

    @Autowired
    private val jwtService: JwtService? = null

    @Autowired
    private val refreshTokenService: RefreshTokenService? = null

    @RequestMapping(value = ["/api/code"], method = [RequestMethod.POST])
    fun sendCode(@RequestParam(value = "telephone") telephone: String): ResponseEntity<String> {

        if (telephoneService?.validatePhoneNumber(telephone) != true) {
            val message = "Некорректный номер телефона."

            logger.info("Код не был отправлен на $telephone. Причина: $message")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message)
        }

        if (telephoneService.sendCode(telephone)) {
            logger.info("Код был выслан на $telephone.")
            return ResponseEntity.ok("Код был выслан")
        }


        logger.debug("Ошибка. Не удалось отправить код на $telephone.")
        // Если не удалось отослать письмо:
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не удалось отправить код")
    }

    @RequestMapping(value = ["/api/confirm"], method = [RequestMethod.POST])
    fun confirmCode(
        @RequestParam telephone: String,
        @RequestParam code: Int,
        @RequestParam fingerprint: String
    ): ResponseEntity<String> {
        TODO()
    }

    @RequestMapping(value = ["/api/refresh"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.OK)
    fun refreshToken(
        @RequestParam email: String,
        @RequestParam refreshToken: UUID,
        @RequestParam fingerprint: String
    ): ResponseEntity<String> {
        TODO()
    }

    @RequestMapping(value = ["/api/auth/bookings/{token}"], method = [RequestMethod.GET])
    @ResponseStatus(HttpStatus.OK)
    fun getBookings(@PathVariable("token") token: String): ResponseEntity<String>? {
        TODO("Проверка токена")

        TODO("Получение юзера по токену")

        TODO("Ретурн букинги у юзера")
    }
}
