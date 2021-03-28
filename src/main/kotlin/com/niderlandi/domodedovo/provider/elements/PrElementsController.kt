package com.niderlandi.domodedovo.provider.elements

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.niderlandi.domodedovo.domain.data.ServiceFormPage
import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import com.niderlandi.domodedovo.domain.data.ServiceProviderResponse
import com.niderlandi.domodedovo.domain.data.enums.ValidationStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class PrElementsController {
    @Autowired
    val prElementsService: PrElementsService? = null

    @RequestMapping(
        value = ["/api/serviceProvider/bookingElements"], method = [RequestMethod.POST], consumes = ["application/json"]
    )
    fun getNextBookingElements(@RequestBody json: Map<String, String>): ServiceProviderResponse {
        val serviceProviderHeader: ServiceProviderHeader =
            jacksonObjectMapper().readValue(json["serviceProviderHeader"], ServiceProviderHeader::class.java)
        val serviceFormPage: ServiceFormPage? =
            if (json.containsKey("serviceFormPage")) {
                jacksonObjectMapper().readValue(json["serviceFormPage"], ServiceFormPage::class.java)
            } else {
                null
            }

        assert(prElementsService != null)

        val validate = prElementsService!!.validate(serviceProviderHeader, serviceFormPage)

        return when (validate.first) {
            ValidationStatus.OK -> {
                if (serviceFormPage == null) {
                    serviceProviderHeader.bookingId = prElementsService!!.generateBookingId()
                    prElementsService!!.storeBookingElements(serviceProviderHeader, serviceFormPage)
                }

                serviceProviderHeader.bookingStatus =
                    prElementsService!!.prebookService(serviceProviderHeader, serviceFormPage).key

                val newPage = prElementsService!!.prepareNextBookingElements(serviceProviderHeader, serviceFormPage)

                val bookingFee = prElementsService!!.calculateBookingFee(serviceProviderHeader)

                ServiceProviderResponse(serviceProviderHeader, newPage, "", bookingFee, "")
            }
            else -> {
                ServiceProviderResponse(serviceProviderHeader, validate.second!!)
            }
        }
    }

}


