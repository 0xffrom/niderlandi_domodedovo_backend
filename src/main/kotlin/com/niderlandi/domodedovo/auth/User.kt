package com.niderlandi.domodedovo.auth

import com.niderlandi.domodedovo.domain.entity.Booking
import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long = 0,

    @Column(name = "telephone")
    val telephone: String = "",

    @Column(name = "booking")
    @OneToMany
    val bookings: List<Booking> = emptyList()
)