package com.niderlandi.domodedovo.booking

import com.niderlandi.domodedovo.data.ServiceProviderHeader
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class BookingController {

    /**
     * Register a certain booking request (i.e. complete the booking process)
     */
    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.PUT])
    fun register(serviceHeader: ServiceProviderHeader) {

    }

    /**
     * Cancel a certain booking request
     */
    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.DELETE])
    fun cancel(serviceHeader: ServiceProviderHeader) {

    }

    /**
     * make a change to a certain booking reques
     */
    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.POST])
    fun changeBooking(serviceHeader: ServiceProviderHeader) {

    }

    /**
     *  Get the booking status
     */
    @RequestMapping(value = ["/serviceBooking/{bookingId}"], method = [RequestMethod.GET])
    fun getBookingStatus(serviceHeader: ServiceProviderHeader) {

    }
}