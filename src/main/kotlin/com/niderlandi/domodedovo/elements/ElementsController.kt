package com.niderlandi.domodedovo.elements

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.domain.data.ServiceFormPage
import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import com.niderlandi.domodedovo.provider.elements.PrElementsController
import com.niderlandi.domodedovo.validation.ValidationController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ElementsController {
    @Autowired
    val validationController: ValidationController? = null

    @Autowired
    val providerPrElementsController: PrElementsController? = null

    @RequestMapping(value = ["/api/bookingElements"], method = [RequestMethod.POST], produces = [PRODUCES_JSON])
    fun postBookingElements(@RequestBody json: Map<String, String>): ResponseEntity<String> {
        val response = providerPrElementsController?.getNextBookingElements(json)

        if (!json.containsKey("serviceFormPage")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("")
        }

        val serviceFormPage = jacksonObjectMapper().readValue(json["serviceFormPage"], ServiceFormPage::class.java)

        // Обращаемся в валидацию.
        val validationStatus = validationController?.validateForm(serviceFormPage = serviceFormPage)

        if (validationStatus != null && validationStatus.statusCode != HttpStatus.OK) {
            return validationStatus
        }

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["/api/form"], method = [RequestMethod.GET], produces = [PRODUCES_JSON])
    @ResponseBody
    fun getNextBookingElements(): ResponseEntity<String> {
        val serviceProviderHeader = ServiceProviderHeader("", bookingId = 0, currentWizardStep = 0)
        val map = mapOf("serviceProviderHeader" to jacksonObjectMapper().writeValueAsString(serviceProviderHeader))

        val response = providerPrElementsController?.getNextBookingElements(map)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    companion object {
        private const val PRODUCES_JSON = "application/json"
    }
}