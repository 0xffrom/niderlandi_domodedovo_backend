package com.niderlandi.domodedovo.validation

import com.niderlandi.domodedovo.domain.data.ServiceElement
import com.niderlandi.domodedovo.domain.data.enums.ContentCategory
import org.springframework.stereotype.Service

@Service
class ValidationService {

    fun validateEmail(serviceElement: ServiceElement): Boolean {
        assert(serviceElement.contentCategory == ContentCategory.email)

        val email = serviceElement.contentValue

        return regexEmail.containsMatchIn(email)
    }


    companion object {
        /**
         * RFC 5322 Official Standard
         */
        private val regexEmail: Regex by lazy {
            Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-" +
                    "\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25" +
                    "[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]" +
                    ":(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
                    "+)\\])")
        }
    }
}