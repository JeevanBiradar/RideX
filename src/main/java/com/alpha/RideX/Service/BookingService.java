package com.alpha.RideX.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.RideX.ResponseStructure;
import com.alpha.RideX.DTO.BookingRequestDTO;
import com.alpha.RideX.DTO.BookingResponseDTO;
import com.alpha.RideX.Entity.Bookings;
import com.alpha.RideX.Entity.Customer;
import com.alpha.RideX.Entity.Driver;
import com.alpha.RideX.Entity.Vechile;
import com.alpha.RideX.Repository.BookingsRepository;
import com.alpha.RideX.Repository.CustomerRepository;
import com.alpha.RideX.Repository.DriverRepository;


@Service
public class BookingService {

    @Autowired
    private BookingsRepository bookingRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private DriverRepository driverRepo;
    @Autowired
    private MapService mapService; 

    public ResponseStructure<BookingResponseDTO> createBooking(BookingRequestDTO request) {

        // Fetch Customer
        Customer customer = customerRepo.findById((int) request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + request.getCustomerId()));

        // Fetch Driver
        Driver driver = driverRepo.findById(request.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + request.getDriverId()));

        Vechile vehicle = driver.getV();

        // Validation: Check if Driver is Available
        if (!"Available".equalsIgnoreCase(vehicle.getAvailablestatus())) {
            throw new RuntimeException("This driver is no longer available. Please choose another.");
        }

        // SECURITY: Re-Calculate Distance & Fare
        double distance = 0.0;
        try {
            double[] sourceCoords = mapService.getCoordinates(request.getSourceLocation());
            double[] destCoords = mapService.getCoordinates(request.getDestinationLocation());

            distance = mapService.getDistanceInKm(
                sourceCoords[0], sourceCoords[1], 
                destCoords[0], destCoords[1]
            );
        } catch (Exception e) {
            throw new RuntimeException("Unable to calculate route. Please check addresses.");
        }

        // Calculate Fare (Distance * Price Per Km)
        double fare = distance * vehicle.getPriceperkm();
        
        // Calculate Time (Distance / Speed)
        int speed = vehicle.getAveragespeed() > 0 ? vehicle.getAveragespeed() : 40;
        double timeInHours = distance / (double) speed;
        String estimatedTime = (int)(timeInHours * 60) + " mins";

        // Create Booking Entity
        Bookings booking = new Bookings();
        booking.setCust(customer);
        booking.setDriver(driver);
        booking.setSourecLocation(request.getSourceLocation());
        booking.setDestinationLocation(request.getDestinationLocation());
        booking.setDistanceTravelled(Math.round(distance * 100.0) / 100.0);
        booking.setFare(Math.round(fare * 100.0) / 100.0);
        booking.setEstimationTravelTime(estimatedTime);
        booking.setBookingDate(LocalDateTime.now());

        // Save Booking
        Bookings savedBooking = bookingRepo.save(booking);

        // Update Vehicle Status to 'Booked'
        vehicle.setAvailablestatus("Booked");
        driverRepo.save(driver); // Saving driver cascades to vehicle

        // Prepare Response DTO
        BookingResponseDTO response = new BookingResponseDTO();
        response.setBookingId(savedBooking.getId());
        response.setStatus("CONFIRMED");
        response.setCustomerName(customer.getName());
        response.setDriverName(driver.getName());
        response.setVehicleModel(vehicle.getModel());
        response.setVehicleNumber(vehicle.getVno());
        response.setVechileType(vehicle.getVtype()); 
        response.setVehicleCapacity(vehicle.getCapacity());
        response.setTotalFare(savedBooking.getFare());
        response.setTotalDistance(savedBooking.getDistanceTravelled());
        response.setEstimatedTime(savedBooking.getEstimationTravelTime());
        response.setBookingTime(savedBooking.getBookingDate());

        // 9. Return Structure
        ResponseStructure<BookingResponseDTO> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Ride Confirmed successfully!");
        rs.setData(response);

        return rs;
    }
}