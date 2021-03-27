package com.otherapi.service.elements

import com.niderlandi.domodedovo.data.BookingFee
import com.niderlandi.domodedovo.data.BookingStatus
import com.niderlandi.domodedovo.data.ServiceFormPage
import com.niderlandi.domodedovo.data.ServiceProviderHeader
import com.otherapi.service.Status
import com.otherapi.service.ValidationStatus
import com.otherapi.service.booking.Booking
import com.otherapi.service.booking.BookingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ElementsService {
    @Qualifier("bookingRepository")
    @Autowired
    private val bookingRepository: BookingRepository? = null

    fun validate(
        serviceProviderHeader: ServiceProviderHeader,
        serviceFormPage: ServiceFormPage? = null
    ): Pair<ValidationStatus, ServiceFormPage?> {
        when (serviceFormPage == null) {
            true -> return Pair(ValidationStatus.OK, null)
            false -> {
                TODO("Тут проводим валидация взависимости от формы.")
            }
        }
    }

    fun prepareNextBookingElements(
        serviceProviderHeader: ServiceProviderHeader,
        serviceFormPage: ServiceFormPage? = null
    ): ServiceFormPage {
        TODO("Вовзращаем форму")
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

    fun storeBookingElements(serviceProviderHeader: ServiceProviderHeader, serviceFormPage: ServiceFormPage) {
        val id = serviceProviderHeader.bookingId

        when (bookingRepository == null) {
            true -> Status.ERROR
            false -> {
                bookingRepository.save(Booking(id))
                Status.OK
            }
        }
    }

    fun generateBookingId(): Long {
        if (bookingRepository == null || bookingRepository.count() == 0L)
            return 0

        return bookingRepository.findAll().last()!!.id + 1
    }

    companion object {
        private const val MOCK_PRE_TAX_FEE_MIN = 1000
        private const val MOCK_PRE_TAX_FEE_MAX = 10000

        private const val MOCK_PRE_TAX_RATE_MIN = 0
        private const val MOCK_PRE_TAX_RATE_MAX = 100
    }
}