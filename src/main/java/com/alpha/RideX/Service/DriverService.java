package com.alpha.RideX.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alpha.RideX.ResponseStructure;
import com.alpha.RideX.DTO.AvailableVehiclesDTO;
import com.alpha.RideX.DTO.CustomerDTO;
import com.alpha.RideX.DTO.RegDriverVehicleDTO;
import com.alpha.RideX.DTO.VehicledetailsDTO;
import com.alpha.RideX.Entity.Customer;
import com.alpha.RideX.Entity.Driver;
import com.alpha.RideX.Entity.Vechile;
import com.alpha.RideX.Exception.CustomerNotFoundWithMobile;
import com.alpha.RideX.Exception.CustomeralreadyExists;
import com.alpha.RideX.Exception.DriverNOtFoundWiththismobileNO;
import com.alpha.RideX.Exception.VehiclesareNotavilabletoDestinationLocation;
import com.alpha.RideX.Repository.CustomerRepository;
import com.alpha.RideX.Repository.DriverRepository;
import com.alpha.RideX.Repository.VechileRepository;




@Service
public class DriverService {

	    @Autowired
	    private DriverRepository driverrepo;
	    
	    @Autowired
	    private CustomerRepository customerRepo;

	    @Autowired
	    private LocationService locationService;
	    
	    @Autowired
	    private VechileRepository vehiclerepo;
	    
	    @Autowired
	    private MapService mapService;

//saveregDriver
	    public ResponseStructure<Driver> saveRegDriver(RegDriverVehicleDTO dto) {

	        // Fetch city using LocationService
	        String city = locationService.getCityFromCoordinates(dto.getLatitude(),dto.getLongitude());

	        Driver driver = new Driver();
	        driver.setLicenseNo(dto.getLicenceNo());
	        driver.setName(dto.getName());
	        driver.setAge(dto.getAge());
	        driver.setMailid(dto.getMail());
	        driver.setGender(dto.getGender());
	        driver.setMobno(dto.getMobileNo());
	        driver.setUpiid(dto.getUpiId());
	        driver.setStatus("Available");
	        

	        Vechile vehicle = new Vechile();
	        
	        vehicle.setDriver(driver);
	        
	        vehicle.setVname(dto.getVehicleName());
	        vehicle.setVno(dto.getVehicleNo());;
	        vehicle.setVtype(dto.getType());
	        vehicle.setModel(dto.getModel());
	        vehicle.setCapacity(dto.getCapacity());
	        vehicle.setCurrentcity(city);
	        vehicle.setAvailablestatus("Available");
	        vehicle.setPriceperkm(dto.getPricePerKm());
	        vehicle.setAveragespeed(dto.getAverageSpeed());

	        driver.setV(vehicle);

	        driverrepo.save(driver);

	        ResponseStructure<Driver> resp = new ResponseStructure<>();
	        resp.setStatusCode(HttpStatus.CREATED.value());
	        resp.setMessage("Driver & Vehicle saved successfully");
	        resp.setData(driver);

	        return resp;
	    }

//findDriver
		public ResponseStructure<Driver> findDriver(long mobileNo) {
			 Driver driver = driverrepo.findBymobno(mobileNo);
			 if(driver==null) {
				 throw new DriverNOtFoundWiththismobileNO("Driver with"+ " "+mobileNo +" "+"not found");
			 }
			
			 
			 ResponseStructure<Driver> rs =new ResponseStructure<Driver>();
				
				rs.setStatusCode(HttpStatus.FOUND.value());
				rs.setMessage("Driver with mobileNo " + mobileNo + "found succesfully");
				rs.setData(driver);
				return rs;
		}
	    
		
//updatedriver
		public ResponseStructure<Driver> updateDriver(double lattitude, double longitude, Long mobilenumber) {
		      
			 Driver driver = driverrepo.findBymobno(mobilenumber);
		      
		      if(driver==null) {
					 throw new DriverNOtFoundWiththismobileNO("Driver with"+ " "+mobilenumber +" "+"not found");
				 }
		      
		      String city = locationService.getCityFromCoordinates(lattitude, longitude);
		      Vechile v = driver.getV();
		      v.setCurrentcity(city);
		      driver.setV(v);
		      driverrepo.save(driver);
		      
		      
		      ResponseStructure<Driver> Rs = new ResponseStructure();
		      Rs.setStatusCode(HttpStatus.ACCEPTED.value());
		      Rs.setMessage("Location updated");
		      Rs.setData(driver);
		      return Rs;
		   }

	
//delete_driver
		public ResponseStructure<Driver> deleteById(long mobileNo)
		{
			Driver driver = driverrepo.findBymobno(mobileNo);
			if(driver==null) {
				 throw new DriverNOtFoundWiththismobileNO("Driver with "+ mobileNo +" not found");
			 }
			driverrepo.delete(driver);
			ResponseStructure<Driver> rs= new ResponseStructure<Driver>();
			rs.setData(driver);
			rs.setMessage("deleted successful");
			rs.setStatusCode(HttpStatus.OK.value());
			return rs;
		}


//RegisterCustomer	
				public ResponseStructure<Customer> registerCustomer(CustomerDTO cdto) {
					
					Customer cust= customerRepo.findByMobileno(cdto.getMobileNo());
					if(cust!=null) {
						throw new CustomeralreadyExists("customer with "+ cdto.getMobileNo()+" already exists");
					}
					Customer customer=new Customer();
					
					customer.setName(cdto.getName());
					customer.setAge(cdto.getAge());
					customer.setGender(cdto.getGender());
					customer.setMobileno(cdto.getMobileNo());
					customer.setMail(cdto.getEmail());
					customer.setCurrentLoc(locationService.getCityFromCoordinates(cdto.getLatitude(),cdto.getLongitude()));
					customerRepo.save(customer);
					ResponseStructure<Customer> rs= new ResponseStructure<Customer>();
					rs.setData(customer);
					rs.setMessage("saved cousterm");
					rs.setStatusCode(HttpStatus.CREATED.value());
					return rs;		
				}		
		

//findCustomer
		public ResponseStructure<Customer> findCustomer(long mobileno) {
			
			Customer cust =customerRepo.findByMobileno(mobileno);
			 if(cust==null) {
				 throw new  CustomerNotFoundWithMobile("customer with "+mobileno+" not found");
			 }
		
			      ResponseStructure<Customer> rs =new ResponseStructure<Customer>();
					
					rs.setStatusCode(HttpStatus.FOUND.value());
					rs.setMessage("customerwith mobileNo " +  mobileno + " found succesfully");
					rs.setData(cust);
					return rs;
		}
			
			

//DeletCustomerByMbNo
		public ResponseStructure<Customer> deleteCustomerBymbno(long mobileno) {
			
			Customer cust =customerRepo.findByMobileno(mobileno);
			 if(cust==null) {
				 throw new  CustomerNotFoundWithMobile("customer with "+mobileno+" not found");
			 }
			
			customerRepo.delete(cust);
			ResponseStructure<Customer> rs= new ResponseStructure<Customer>();
			rs.setData(cust);
			rs.setMessage("delete coustmer by mobno "+mobileno);
			rs.setStatusCode(HttpStatus.CREATED.value());
			return rs;
		}
		

//getallAvailableVehicles		
	public ResponseStructure<AvailableVehiclesDTO> getAvailableVehiclesByCity(Long mobileno, String destinationLocation,String inputSource) {
					
		Customer c=customerRepo.findByMobileno(mobileno);
		if(c==null) {
			throw new CustomerNotFoundWithMobile("Customer with"+" "+mobileno+" "+"not found");
		}
					
		//we will get customer location (current city)
		String SourceLocation = (inputSource != null && !inputSource.isEmpty()) ? inputSource : c.getCurrentLoc();
					
		double calculatedDistance;
		try {                                    //this method will give us latitude and longitude based on city name
			  double[] sourceCoords = mapService.getCoordinates(SourceLocation);//customer location
			  double[] destCoords = mapService.getCoordinates(destinationLocation);//we will get from request when we call allAvailabelvechile method
			            
			  //using above 2 coordinates of source and destination this method will calculate distance
			  calculatedDistance = mapService.getDistanceInKm(
              sourceCoords[0], sourceCoords[1], 
              destCoords[0], destCoords[1]);
			        } catch (Exception e) {
			            throw new RuntimeException("Location Error: " + e.getMessage());
			        }				
					
					
			  AvailableVehiclesDTO AVD=new AvailableVehiclesDTO();
					
		      AVD.setC(c);
			  AVD.setSourceLocation(SourceLocation);
			  AVD.setDistance(calculatedDistance);
			  AVD.setDestinationLocation(destinationLocation);
					
			  
			  //empty list to store processed vechile later
			  List<VehicledetailsDTO> l= new ArrayList<VehicledetailsDTO>();
							
			  //store vechiles based on filter (sourceloaction/customerlocation)
			  List<Vechile> list=vehiclerepo.findByCurrentcityIgnoreCaseAndAvailablestatus(SourceLocation,"Available");
					
			  if(list ==null || list.isEmpty()) {
						throw new VehiclesareNotavilabletoDestinationLocation("No vechile availalable in "+SourceLocation);
					}
					
			  //iterate through vechile list and set dto datafilds and calculate fare and time
					for (Vechile vehicle : list) {
						
						VehicledetailsDTO vd= new VehicledetailsDTO();
						
						int speed=vehicle.getAveragespeed();
						double price=vehicle.getPriceperkm();
						
						double fare=price*calculatedDistance;
						
						double time=calculatedDistance/(double)speed;
						
						vd.setV(vehicle);
						vd.setFare(fare);
						vd.setEstimationTIme(time);
					
						//add this to vechiledto list (processed vechiles)
						l.add(vd);
						
						
					}
					
					//add list of availbe vechile to availablevechiledto class
					AVD.setAvailabeVehicles(l);
					
					ResponseStructure <AvailableVehiclesDTO>  rs=new ResponseStructure<AvailableVehiclesDTO>();
					rs.setStatusCode(HttpStatus.ACCEPTED.value());
					rs.setMessage("Available vehicles fetched successfully");
					rs.setData(AVD);
					return rs;
				}
}
