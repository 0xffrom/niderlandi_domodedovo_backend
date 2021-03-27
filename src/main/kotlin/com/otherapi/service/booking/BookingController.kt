package com.otherapi.service.booking

import com.niderlandi.domodedovo.data.ServiceProviderHeader
import com.niderlandi.domodedovo.data.ServiceProviderResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Mock Service
 */
@Controller
class BookingController {
    @Autowired
    val bookingService: BookingService? = null

    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.GET])
    fun checkBookingStatus(@PathVariable("bookingId") bookingId: Long): ServiceProviderResponse {
        TODO("")
    }

    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.PUT])
    fun registerBooking(@PathVariable("bookingId") bookingId : Long) : ServiceProviderResponse{
        TODO("")
    }

    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.DELETE])
    fun cancelBooking(@PathVariable("bookingId") bookingId : Long) : ServiceProviderResponse{
        TODO()
    }

}
