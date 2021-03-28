package com.niderlandi.domodedovo.provider.booking

import com.niderlandi.domodedovo.domain.entity.Booking
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@EnableJpaRepositories
@Repository("bookingRepository")
interface PrBookingRepository : CrudRepository<Booking, Long> {

}