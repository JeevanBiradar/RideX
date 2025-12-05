package com.alpha.RideX.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.RideX.Entity.Driver;
import com.alpha.RideX.Repository.DriverRepository;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository dr;
	
	
	public Driver getDriverByMobile(long mobno)
	{
        
		Driver d = dr.findBymobno(mobno);
		
		if(d == null)
		{
			System.out.println("Driver not found!");
		}
		return d;
	}

	public String deleteDriverByMobile(long mobno)
	{
		Driver d = dr.findBymobno(mobno);
		
		if(d == null)
		{
			return "Driver not found!";
		}
		
		dr.delete(d);
		return "Driver deleted successfully!";
	}

	public Driver savedriver(Driver d) {
		
		dr.save(d);
		return d;
	}
}
