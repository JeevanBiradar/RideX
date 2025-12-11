package com.alpha.RideX.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.RideX.Entity.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Integer>{

	List<Bookings> findByCust_Mobileno(long mobileno);

    
    List<Bookings> findByDriver_Mobno(long mobno);
}
