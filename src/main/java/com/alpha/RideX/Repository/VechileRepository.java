package com.alpha.RideX.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.RideX.Entity.Vechile;

public interface VechileRepository extends JpaRepository<Vechile, Integer>{

	List<Vechile> findAvailableVehiclesBycurrentcity(String currentcity);

}
