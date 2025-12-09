package com.alpha.RideX.Service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

	@Autowired
	private RestTemplate restTemplate;
	
	private final String API_KEY = "pk.a164fa34762abfa8d20feda51a0cc237";
	
	public String getCityFromCoordinates(double latitude,double longitude)
	{
		String url = "https://us1.locationiq.com/v1/reverse.php?key=" + API_KEY +"&lat="+latitude+"&lon="+longitude+"&format=json";
		
		Map<String,Object> response = restTemplate.getForObject(url, Map.class);
		
		Map<String,Object> address = (Map<String,Object>)response.get("address");
		
		return (String)address.get("city");
	}
}
