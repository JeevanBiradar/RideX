package com.alpha.RideX.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.RideX.Entity.Bookings;
import com.alpha.RideX.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByBooking(Bookings booking);
}
