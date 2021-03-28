package com.niderlandi.domodedovo.telephone

import org.springframework.stereotype.Service

@Service
class TelephoneService {

    // TODO: Заменить на статус вместо була
    fun sendCode(telephone : String) : Boolean {

        // TODO: Дописать метод для отправки сообщения. Временный мок:
        return true
    }

    fun validatePhoneNumber(telephone: String) : Boolean{
        return true
    }

    // TODO: Заменить на статус вместо була
    fun validateCode(telephone: String, code : Int) : Boolean{
        return true
    }
}