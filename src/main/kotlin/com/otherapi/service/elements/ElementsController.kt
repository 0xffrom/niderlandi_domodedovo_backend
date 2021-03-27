package com.otherapi.service.elements

import com.niderlandi.domodedovo.data.ServiceFormPage
import com.niderlandi.domodedovo.data.ServiceProviderHeader
import com.niderlandi.domodedovo.data.ServiceProviderResponse
import com.otherapi.service.ValidationStatus
import com.otherapi.service.booking.Booking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ElementsController {
    @Autowired
    val elementsService: ElementsService? = null


    @RequestMapping(value = ["/bookingElements"], method = [RequestMethod.DELETE])
    fun getNextBookingElements(
        @RequestParam serviceProviderHeader: ServiceProviderHeader,
        @RequestParam serviceFormPage: ServiceFormPage? = null
    ): ServiceProviderResponse {
        assert(elementsService != null)

        val validate = elementsService!!.validate(serviceProviderHeader, serviceFormPage)

        return when (validate.first) {
            ValidationStatus.OK -> {
                if(serviceFormPage != null && serviceProviderHeader.bookingId == Booking.DEFAULT_ID){
                    serviceProviderHeader.bookingId = elementsService!!.generateBookingId()

                    elementsService!!.storeBookingElements(serviceProviderHeader, serviceFormPage)
                }
                serviceProviderHeader.bookingStatus =
                    elementsService!!.prebookService(serviceProviderHeader, serviceFormPage).key

                val newPage = elementsService!!.prepareNextBookingElements(serviceProviderHeader, serviceFormPage)

                val bookingFee = elementsService!!.calculateBookingFee(serviceProviderHeader)

                ServiceProviderResponse(serviceProviderHeader, newPage, "", bookingFee, "")
            }
            else -> {
                ServiceProviderResponse(serviceProviderHeader, validate.second!!)
            }
        }
    }

}


