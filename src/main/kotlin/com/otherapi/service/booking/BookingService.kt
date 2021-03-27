package com.otherapi.service.booking


import com.niderlandi.domodedovo.data.BookingStatus
import com.niderlandi.domodedovo.data.ServiceProviderHeader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BookingService {
    @Qualifier("bookingRepository")
    @Autowired
    private val bookingRepository: BookingRepository? = null

    fun getBookingStatus(serviceProviderHeader: ServiceProviderHeader): Int {
        val booking = bookingRepository?.findById(serviceProviderHeader.bookingId)

        if(booking != null && booking.isPresent){
            return booking.get().bookingStatus
        }

        return BookingStatus.UNDEFINED.key
    }

    fun registerBooking(bookingId : Long): Int {
        return try {
            bookingRepository?.save(Booking(bookingId, BookingStatus.REGISTERED.key))

            BookingStatus.REGISTERED.key
        } catch (e : Exception){
            BookingStatus.UNDEFINED.key
        }
    }

    fun cancelBooking(bookingId : Long): Int {
        val booking = bookingRepository?.findById(bookingId)

        if(booking != null && booking.isPresent){
            bookingRepository?.save(booking.get().apply {
                bookingStatus = BookingStatus.CANCELED.key
            })

            return BookingStatus.CANCELED.key
        }
        return BookingStatus.UNDEFINED.key
    }

}