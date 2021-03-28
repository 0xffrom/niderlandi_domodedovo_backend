package com.niderlandi.domodedovo.provider.booking

import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import com.niderlandi.domodedovo.domain.data.ServiceProviderResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Mock Service
 */
@Controller
class PrBookingController {
    @Autowired
    val prBookingService: PrBookingService? = null

    @RequestMapping(value = ["/api/serviceProvider/serviceBooking/{bookingId}"], method = [RequestMethod.GET])
    fun checkBookingStatus(@PathVariable("bookingId") bookingId: Long): ServiceProviderResponse {

        val bookingStatus =
            prBookingService?.getBookingStatus(ServiceProviderHeader("3", bookingId = bookingId, currentWizardStep = 10))

        return ServiceProviderResponse(
            ServiceProviderHeader("3", bookingId = bookingId, currentWizardStep = 12, bookingStatus = bookingStatus),
            null,
            null,
            null,
            null
        )
    }

    @RequestMapping(value = ["/api/serviceProvider/serviceBooking/{bookingId}"], method = [RequestMethod.PUT])
    fun registerBooking(@PathVariable("bookingId") bookingId: Long): ServiceProviderResponse {

        return ServiceProviderResponse(
            ServiceProviderHeader("3", bookingId = bookingId, currentWizardStep = 12),
            null,
            null,
            null,
            null
        )
    }

    @RequestMapping(value = ["/api/serviceProvider/serviceBooking/{bookingId}"], method = [RequestMethod.DELETE])
    fun cancelBooking(@PathVariable("bookingId") bookingId: Long): ServiceProviderResponse {
        prBookingService?.cancelBooking(bookingId)

        return ServiceProviderResponse(
            ServiceProviderHeader("3", bookingId = bookingId, currentWizardStep = 12),
            null,
            null,
            null,
            null
        )
    }

}
