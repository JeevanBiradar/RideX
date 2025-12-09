package com.alpha.RideX.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.RideX.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findByMobileno(Long mobileno);
    
}
