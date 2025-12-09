package com.alpha.RideX.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {

    @Value("${api.key.locationiq}")
    private String locationIqToken;

    @Value("${api.key.openrouteservice}")
    private String orsApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

   
    public double[] getCoordinates(String address) {
        String url = "https://us1.locationiq.com/v1/search?key=" + locationIqToken + "&q=" + address + "&format=json";

        try {
            String response = restTemplate.getForObject(url, String.class);
            JSONArray jsonArray = new JSONArray(response);

            if (jsonArray.length() > 0) {
                JSONObject location = jsonArray.getJSONObject(0);
                return new double[]{
                    location.getDouble("lat"),
                    location.getDouble("lon")
                };
            }
        } catch (Exception e) {
        	
            System.err.println("MapService Error: " + e.getMessage());
        }
        throw new RuntimeException("Coordinates not found for: " + address);
    }

  //Distance Calculate
    public double getDistanceInKm(double startLat, double startLon, double endLat, double endLon) {
        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + orsApiKey 
                   + "&start=" + startLon + "," + startLat 
                   + "&end=" + endLon + "," + endLat;

        try {
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);

            // Extract distance from nested JSON
            double distanceMeters = json.getJSONArray("features")
                    .getJSONObject(0)
                    .getJSONObject("properties")
                    .getJSONArray("segments")
                    .getJSONObject(0)
                    .getDouble("distance");

            return distanceMeters / 1000.0; // Convert to KM

        } catch (Exception e) {
            throw new RuntimeException("Route calculation failed: " + e.getMessage());
        }
    }
}