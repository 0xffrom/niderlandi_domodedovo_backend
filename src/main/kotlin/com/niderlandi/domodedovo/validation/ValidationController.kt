package com.niderlandi.domodedovo.validation

import com.niderlandi.domodedovo.domain.data.ServiceFormPage
import com.niderlandi.domodedovo.domain.data.enums.ContentCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class ValidationController {
    @Autowired
    private val validationService: ValidationService? = null

    @RequestMapping(value = ["/api/validation/}"], method = [RequestMethod.GET])
    fun validateForm(@RequestBody serviceFormPage: ServiceFormPage): ResponseEntity<String> {
        val validation = serviceFormPage.serviceFormElements.asSequence().flatMap { serviceFormElement ->
            serviceFormElement.serviceElements.map { serviceElement ->
                when (serviceElement.contentCategory) {
                    ContentCategory.email -> validationService?.validateEmail(serviceElement)
                    else -> true
                }
            }
        }.all { it == true }

        return when(validation){
            true -> ResponseEntity.ok("Success")
            else ->  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error")
        }
    }
}