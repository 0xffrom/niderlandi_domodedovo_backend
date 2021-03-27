package com.otherapi.service.booking

import com.niderlandi.domodedovo.data.BookingStatus
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Booking(
    @Id
    val id: Long = DEFAULT_ID,
    var bookingStatus: Int = BookingStatus.UNDEFINED.key
) {
    companion object {
        const val DEFAULT_ID = 0L
    }
}