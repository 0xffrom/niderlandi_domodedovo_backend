package com.niderlandi.domodedovo.domain.entity

import com.niderlandi.domodedovo.domain.data.enums.BookingStatus
import javax.persistence.*

@Entity
data class Booking(
    @Id
    @Column(name = "id")
    val id: Long = DEFAULT_ID,
    var bookingStatus: Int = BookingStatus.UNDEFINED.key,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], targetEntity = Person::class)
    var names: List<Person> = emptyList(),

    // Рейс
    var flightNumber: String = "",

    // Время встречи
    var meetingTime: String = "",

    // Категория пассажира
    var categoryPass: String = "",

    // Точка встречи
    var meetingPoint: String = "",

    // Особенности
    var qualities: String = "",

    var telephone: String = "",

    var email: String = "",

    var date: String = "",
) {
    companion object {
        const val DEFAULT_ID = 0L
    }
}