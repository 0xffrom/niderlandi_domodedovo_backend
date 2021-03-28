package com.niderlandi.domodedovo.booking

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class BookingController {
    @Autowired
    private val providerBookingController: BookingController? = null

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.PUT])
    fun register(serviceHeader: ServiceProviderHeader): ResponseEntity<String> {
        val response = providerBookingController?.register(serviceHeader)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.DELETE])
    fun cancel(serviceHeader: ServiceProviderHeader): ResponseEntity<String> {
        val response = providerBookingController?.cancel(serviceHeader)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.POST])
    fun changeBooking(serviceHeader: ServiceProviderHeader): ResponseEntity<String> {
        val response = providerBookingController?.changeBooking(serviceHeader)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.GET])
    fun getBookingStatus(serviceHeader: ServiceProviderHeader): ResponseEntity<String> {
        val response = providerBookingController?.getBookingStatus(serviceHeader)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }
}