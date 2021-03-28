package com.niderlandi.domodedovo.elements

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import com.niderlandi.domodedovo.provider.elements.ElementsController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ElementsController {
    @Autowired
    val providerElementsController: ElementsController? = null

    @RequestMapping(value = ["/bookingElements"], method = [RequestMethod.POST], produces = [PRODUCES_JSON])
    fun postBookingElements(@RequestBody json: Map<String, String>): ResponseEntity<String> {
        val response = providerElementsController?.getNextBookingElements(json)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["/api/form"], method = [RequestMethod.GET], produces = [PRODUCES_JSON])
    @ResponseBody
    fun getNextBookingElements(): ResponseEntity<String> {
        val serviceProviderHeader = ServiceProviderHeader("", bookingId = 0, currentWizardStep = 0)
        val map = mapOf("serviceProviderHeader" to jacksonObjectMapper().writeValueAsString(serviceProviderHeader))

        val response = providerElementsController?.getNextBookingElements(map)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    companion object{
        private const val PRODUCES_JSON = "application/json"
    }
}