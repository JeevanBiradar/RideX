package com.alpha.RideX.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.RideX.Entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer>{

	Driver findBymobno(long mobno);
	
	void deleteBymobno(long mobno);
}
