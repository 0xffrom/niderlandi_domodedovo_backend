package com.niderlandi.domodedovo.booking

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import com.niderlandi.domodedovo.provider.booking.PrBookingController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class BookingController {
    @Autowired
    private val providerPrBookingController: PrBookingController? = null

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.PUT])
    fun register(serviceHeader: ServiceProviderHeader): ResponseEntity<String> {
        val response = providerPrBookingController?.registerBooking(serviceHeader.bookingId)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.DELETE])
    fun cancel(serviceHeader: ServiceProviderHeader): ResponseEntity<String> {
        val response = providerPrBookingController?.cancelBooking(serviceHeader.bookingId)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.GET])
    fun getBookingStatus(serviceHeader: ServiceProviderHeader): ResponseEntity<String> {
        val response = providerPrBookingController?.checkBookingStatus(serviceHeader.bookingId)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }
}