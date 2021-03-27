package com.niderlandi.domodedovo.elements

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.data.ServiceFormPage
import com.niderlandi.domodedovo.data.ServiceProviderHeader
import com.niderlandi.domodedovo.data.ServiceProviderResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BookingElementsController {

    /**
     * First step. Create service form page and calculate
     */
    @RequestMapping(value = ["/bookingElements}"], method = [RequestMethod.POST])
    fun get(@RequestParam serviceHeader: ServiceProviderHeader): ResponseEntity<ObjectMapper> {
        // TODO: Fetches the next booking data by posting the previous elements (if available)

        serviceHeader.apply {
            bookingId = 1 // TODO: Добавить в БД
        }
        val response = ServiceProviderResponse(serviceHeader, ServiceFormPage(), null, null, null)

        return ResponseEntity.ok(jacksonObjectMapper().apply {
            writeValueAsString(response)
        })
    }

    @RequestMapping(value = ["/bookingElements}"], method = [RequestMethod.POST])
    fun postBookingElements(
        @RequestParam serviceHeader: ServiceProviderHeader,
        @RequestParam serviceFormPage: ServiceFormPage
    ) {
        // TODO: Fetches the next booking data by posting the previous elements (if available)
        return
    }
}