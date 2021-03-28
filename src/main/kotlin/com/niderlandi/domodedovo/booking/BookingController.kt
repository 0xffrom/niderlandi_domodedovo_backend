package com.niderlandi.domodedovo.booking

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import com.niderlandi.domodedovo.provider.booking.PrBookingController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class BookingController {
    @Autowired
    private val providerPrBookingController: PrBookingController? = null

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.PUT])
    fun register(@PathVariable("bookingId") bookingId: Long): ResponseEntity<String> {
        val response = providerPrBookingController?.registerBooking(bookingId)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.DELETE])
    fun cancel(@PathVariable("bookingId") bookingId: Long): ResponseEntity<String> {
        val response = providerPrBookingController?.cancelBooking(bookingId)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }

    @RequestMapping(value = ["api/serviceBooking/{bookingId}"], method = [RequestMethod.GET])
    fun getBookingStatus(@PathVariable("bookingId") bookingId: Long): ResponseEntity<String> {
        val response = providerPrBookingController?.checkBookingStatus(bookingId)

        return ResponseEntity.ok(jacksonObjectMapper().writeValueAsString(response))
    }
}