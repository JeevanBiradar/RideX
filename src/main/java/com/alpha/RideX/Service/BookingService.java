package com.alpha.RideX.Service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import com.alpha.RideX.ResponseStructure;
import com.alpha.RideX.DTO.BookingRequestDTO;
import com.alpha.RideX.DTO.BookingResponseDTO;
import com.alpha.RideX.Entity.*; // Imports Booking, Payment, Customer, Driver, Enums
import com.alpha.RideX.Repository.*;

@Service
public class BookingService {

    @Autowired
    private BookingsRepository bookingRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private VechileRepository vehicleRepo;
    @Autowired
    private DriverRepository driverRepo;
    @Autowired
    private PaymentRepository paymentRepo;
    @Autowired
    private MapService mapService;

    public ResponseStructure<BookingResponseDTO> createBooking(BookingRequestDTO request) {

        // 1. FETCH ENTITIES
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Vechile vehicle = vehicleRepo.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // 2. CHECK AVAILABILITY (Sync Check)
        // Ideally, check both. If driver is offline, car shouldn't be bookable.
        if (!"Available".equalsIgnoreCase(vehicle.getAvailablestatus())) {
            throw new RuntimeException("Vehicle is already booked.");
        }
        
        Driver driver = vehicle.getDriver();
        if (driver == null) throw new RuntimeException("No driver assigned to this vehicle");
        
        if (!"Available".equalsIgnoreCase(driver.getStatus())) {
             throw new RuntimeException("Driver is currently unavailable.");
        }

        // 3. CALCULATE DISTANCE & FARE
        double distance = 0.0;
        try {
            double[] src = mapService.getCoordinates(request.getSourceLocation());
            double[] dest = mapService.getCoordinates(request.getDestinationLocation());
            distance = mapService.getDistanceInKm(src[0], src[1], dest[0], dest[1]);
        } catch (Exception e) {
            throw new RuntimeException("Map Error: " + e.getMessage());
        }

        double fare = distance * vehicle.getPriceperkm();
        
        // Safe speed check
        int speed = (vehicle.getAveragespeed() > 0) ? vehicle.getAveragespeed() : 40;
        int timeMinutes = (int) ((distance / speed) * 60);

        // 4. CREATE BOOKING
        Bookings booking = new Bookings();
        booking.setCust(customer);
        booking.setDriver(driver);
        booking.setSourecLocation(request.getSourceLocation());
        booking.setDestinationLocation(request.getDestinationLocation());
        booking.setDistanceTravelled(Math.round(distance * 100.0) / 100.0);
        booking.setFare(Math.round(fare * 100.0) / 100.0);
        booking.setEstimationTravelTime(timeMinutes + " mins");
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookingDate(LocalDateTime.now());

        // 5. UPDATE JAVA LISTS (Consistency)
        if(customer.getBookinglist() != null) customer.getBookinglist().add(booking);
        if(driver.getBookinglist() != null) driver.getBookinglist().add(booking);

        // 6. SAVE BOOKING
        Bookings savedBooking = bookingRepo.save(booking);

        // 7. CREATE PAYMENT (Normalized)
        Payment payment = new Payment();
        payment.setBooking(savedBooking); 
        payment.setAmount(savedBooking.getFare());
        // Handle String to Enum conversion safely, or just store String if Entity uses String
        payment.setPaymentType(request.getPaymentMethod()); 
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentTime(LocalDateTime.now());
        
        paymentRepo.save(payment);

        // 8. SYNC STATUS: LOCK BOTH VEHICLE AND DRIVER
        vehicle.setAvailablestatus("Booked");
        vehicleRepo.save(vehicle);

        driver.setStatus("On Trip");
        driverRepo.save(driver);

        // 9. RESPONSE
        BookingResponseDTO responseDTO = new BookingResponseDTO(
            savedBooking.getId(),
            savedBooking.getFare(),
            savedBooking.getDistanceTravelled(),
            savedBooking.getEstimationTravelTime(),
            savedBooking.getStatus(),
            payment.getPaymentStatus(),
            driver.getName(),
            vehicle.getModel(),
            savedBooking.getBookingDate()
        );

        ResponseStructure<BookingResponseDTO> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Ride Confirmed");
        rs.setData(responseDTO);

        return rs;
    }
}