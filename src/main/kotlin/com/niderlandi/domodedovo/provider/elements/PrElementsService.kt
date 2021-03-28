package com.niderlandi.domodedovo.provider.elements

import com.niderlandi.domodedovo.domain.data.BookingFee
import com.niderlandi.domodedovo.domain.data.ServiceFormPage
import com.niderlandi.domodedovo.domain.data.ServiceProviderHeader
import com.niderlandi.domodedovo.domain.data.enums.BookingStatus
import com.niderlandi.domodedovo.domain.data.enums.Status
import com.niderlandi.domodedovo.domain.data.enums.ValidationStatus
import com.niderlandi.domodedovo.domain.entity.Booking
import com.niderlandi.domodedovo.provider.booking.PrBookingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PrElementsService {
    @Qualifier("bookingRepository")
    @Autowired
    private val prBookingRepository: PrBookingRepository? = null

    fun validate(
        serviceProviderHeader: ServiceProviderHeader,
        serviceFormPage: ServiceFormPage? = null
    ): Pair<ValidationStatus, ServiceFormPage?> {
        return when (serviceFormPage == null) {
            true -> Pair(ValidationStatus.OK, null)
            false -> {
                when (ValidatorFormPage()(serviceFormPage)) {
                    true -> Pair(ValidationStatus.OK, serviceFormPage)
                    else -> Pair(ValidationStatus.ERROR, serviceFormPage)
                }
            }
        }
    }

    fun prepareNextBookingElements(
        serviceProviderHeader: ServiceProviderHeader,
        serviceFormPage: ServiceFormPage? = null
    ): ServiceFormPage {
        return MockElement().serviceFormPage
    }

    fun prebookService(serviceProviderHeader: ServiceProviderHeader, serviceFormPage: ServiceFormPage?): BookingStatus {
        return BookingStatus.REGISTERED
    }

    fun calculateBookingFee(serviceProviderHeader: ServiceProviderHeader): BookingFee {
        return BookingFee(
            Random.nextInt(MOCK_PRE_TAX_FEE_MIN, MOCK_PRE_TAX_FEE_MAX),
            Random.nextInt(MOCK_PRE_TAX_RATE_MIN, MOCK_PRE_TAX_RATE_MAX) + Random.nextFloat(),
            listOf(),
            listOf(),
            "DE${Random.nextInt(0, 10000000)}"
        )
    }

    fun storeBookingElements(serviceProviderHeader: ServiceProviderHeader, serviceFormPage: ServiceFormPage?) {
        val id = serviceProviderHeader.bookingId

        when (prBookingRepository == null) {
            true -> Status.ERROR
            false -> {
                prBookingRepository.save(Booking(id))
                Status.OK
            }
        }
    }

    fun generateBookingId(): Long {
        if (prBookingRepository == null || prBookingRepository.count() == 0L)
            return 0

        return prBookingRepository.findAll().last()!!.id + 1
    }

    companion object {
        private const val MOCK_PRE_TAX_FEE_MIN = 1000
        private const val MOCK_PRE_TAX_FEE_MAX = 10000

        private const val MOCK_PRE_TAX_RATE_MIN = 0
        private const val MOCK_PRE_TAX_RATE_MAX = 100
    }
}