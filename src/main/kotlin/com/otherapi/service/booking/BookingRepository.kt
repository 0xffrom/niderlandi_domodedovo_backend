package com.otherapi.service.booking

import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@EnableJpaRepositories
@Repository("bookingRepository")
interface BookingRepository : CrudRepository<Booking, Long>{

}