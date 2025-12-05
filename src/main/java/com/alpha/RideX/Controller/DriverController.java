package com.alpha.RideX.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.RideX.Entity.Driver;
import com.alpha.RideX.Service.DriverService;

@RestController
public class DriverController {

	@Autowired
	private DriverService ds;
	
	@PostMapping("/savedriver")
	public Driver saveDriver(@RequestBody Driver d)
	{
		return ds.savedriver(d);
	}
	
	@GetMapping("/mobile/{mobno}")
	public Driver getDriverByMobile(@PathVariable long mobno)
	{
		return ds.getDriverByMobile(mobno);
	}
	
	@DeleteMapping("/mobile/{mobno}")
	public String deleteDriverByMboile(@PathVariable long mobno)
	{
		return ds.deleteDriverByMobile(mobno);
	}
	
}
