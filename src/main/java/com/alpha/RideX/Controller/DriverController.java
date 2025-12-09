package com.alpha.RideX.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.RideX.ResponseStructure;
import com.alpha.RideX.DTO.AvailableVehiclesDTO;
import com.alpha.RideX.DTO.CustomerDTO;
import com.alpha.RideX.DTO.RegDriverVehicleDTO;
import com.alpha.RideX.Entity.Customer;
import com.alpha.RideX.Entity.Driver;
import com.alpha.RideX.Service.DriverService;


@RestController
public class DriverController {

	@Autowired
	private DriverService ds;
	
	
	@PostMapping("/registerdriver")
	public ResponseStructure<Driver> saveDriver(@RequestBody RegDriverVehicleDTO dto)
	{
		return ds.saveRegDriver(dto);
	}
	
	@GetMapping("/finddriverbymobno")
	public ResponseStructure<Driver> getDriverByMobile(@RequestParam long mobno)
	{
		return ds.findDriver(mobno);
	}
	
	@PostMapping("/updateDriverlocation")
	public ResponseStructure<Driver> updateMobileByLoc(@RequestParam double latitude, @RequestParam double longitude,@RequestParam Long mobno)
	{
		return ds.updateDriver(latitude,longitude,mobno);
	}
	
	@DeleteMapping("/deleteDriverbyPhoneNO")
	public ResponseStructure<Driver> deleteDriverByMboile(@RequestParam long mobno)
	{
		return ds.deleteById(mobno);
	}
	
	
	@PostMapping("/registercustomer")
	public ResponseStructure<Customer> saveDriver(@RequestBody CustomerDTO dto)
	{
		return ds.registerCustomer(dto);
	}
	
	@GetMapping("/findcustomerbymobno")
	public ResponseStructure<Customer> getCustomerByMobile(@RequestParam long mobno)
	{
		return ds.findCustomer(mobno);
	}
	
	@DeleteMapping("/deleteCustomerbyPhoneNO")
	public ResponseStructure<Customer> deleteCustomerByMboile(@RequestParam long mobno)
	{
		return ds.deleteCustomerBymbno(mobno);
	}
	
	@GetMapping("/available-vehicles")
    public ResponseEntity<ResponseStructure<AvailableVehiclesDTO>> getAvailableVehicles(@RequestParam Long mobileno,@RequestParam String destination) {
        
        ResponseStructure<AvailableVehiclesDTO> response = ds.getAvailableVehiclesByCity(mobileno, destination);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
