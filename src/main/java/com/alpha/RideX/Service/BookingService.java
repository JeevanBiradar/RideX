package com.alpha.RideX.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;

import com.alpha.RideX.ResponseStructure;
import com.alpha.RideX.DTO.BookingRequestDTO;
import com.alpha.RideX.DTO.BookingResponseDTO;
import com.alpha.RideX.Entity.*; 
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

        //FETCH ENTITIES
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Vechile vehicle = vehicleRepo.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        //CHECK AVAILABILITY (Sync Check)
        if (!"Available".equalsIgnoreCase(vehicle.getAvailablestatus())) {
            throw new RuntimeException("Vehicle is already booked.");
        }
        
        Driver driver = vehicle.getDriver();
        if (driver == null) throw new RuntimeException("No driver assigned to this vehicle");
        
        if (!"Available".equalsIgnoreCase(driver.getStatus())) {
             throw new RuntimeException("Driver is currently unavailable.");
        }

        //CALCULATE DISTANCE & FARE
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

        // CREATE BOOKING
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

        //UPDATE JAVA LISTS (Consistency)
        if(customer.getBookinglist() != null) customer.getBookinglist().add(booking);
        if(driver.getBookinglist() != null) driver.getBookinglist().add(booking);

        //SAVE BOOKING
        Bookings savedBooking = bookingRepo.save(booking);

        // CREATE PAYMENT 
        Payment payment = new Payment();
        payment.setBooking(savedBooking); 
        payment.setAmount(savedBooking.getFare());
        payment.setPaymentType(request.getPaymentMethod()); 
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentTime(LocalDateTime.now());
        
        paymentRepo.save(payment);

        // SYNC STATUS: LOCK BOTH VEHICLE AND DRIVER
        vehicle.setAvailablestatus("Booked");
        vehicleRepo.save(vehicle);

        driver.setStatus("On Trip");
        driverRepo.save(driver);

        // RESPONSE
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
    
    @Transactional
    public ResponseStructure<String> completeRide(int bookingId) {
        
        // 1. Fetch Booking
        Bookings booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        // 2. Validate State
        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new RuntimeException("Ride is already completed.");
        }

        // 3. Update Booking Status
        booking.setStatus(BookingStatus.COMPLETED);
        
        
        // 4. Update Payment Status (Simulating payment success upon completion)
        Payment payment = paymentRepo.findByBooking(booking);
        if(payment != null) {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            paymentRepo.save(payment);
        }

        // 5. Free up Driver and Vehicle
        Driver driver = booking.getDriver();
        Vechile vehicle = booking.getDriver().getV();

        if (driver != null) {
            driver.setStatus("Available");
            // Update driver location to destination (Optional but realistic)
            // driver.setLatitude(...); 
            driverRepo.save(driver);
        }

        if (vehicle != null) {
            vehicle.setAvailablestatus("Available");
            // vehicle.setCurrentcity(booking.getDestinationLocation()); 
            // Optional: update location
            
            vehicleRepo.save(vehicle);
        }

        bookingRepo.save(booking);

        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Ride completed successfully. Driver is now available.");
        rs.setData("Booking ID: " + bookingId + " is CLOSED.");

        return rs;
    }

    
    public ResponseStructure<List<BookingResponseDTO>> getBookingHistoryByCustomer(long mobileNo) {
        
        // Check if customer exists
        Customer cust = customerRepo.findByMobileno(mobileNo);
        if (cust == null) throw new RuntimeException("Customer not found");

        List<Bookings> history = bookingRepo.findByCust_Mobileno(mobileNo);
        
       
        List<BookingResponseDTO> historyDTOs = new ArrayList<>();
        
        for (Bookings b : history) {
        	
            PaymentStatus pStatus = b.getPayment().getPaymentStatus(); 
            
            historyDTOs.add(new BookingResponseDTO(
                b.getId(), b.getFare(), b.getDistanceTravelled(), 
                b.getEstimationTravelTime(), b.getStatus(), 
                pStatus, 
                b.getDriver().getName(), 
                b.getDriver().getV().getModel(), 
                b.getBookingDate()
            ));
        }

        ResponseStructure<List<BookingResponseDTO>> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Booking History Fetched");
        rs.setData(historyDTOs);
        return rs;
    }


    public ResponseStructure<List<BookingResponseDTO>> getBookingHistoryByDriver(long mobileNo) {
        
        Driver driver = driverRepo.findBymobno(mobileNo);
        if (driver == null) throw new RuntimeException("Driver not found");

        List<Bookings> history = bookingRepo.findByDriver_Mobno(mobileNo);
        
        List<BookingResponseDTO> historyDTOs = new ArrayList<>();
        for (Bookings b : history) {
            historyDTOs.add(new BookingResponseDTO(
                b.getId(), b.getFare(), b.getDistanceTravelled(), 
                b.getEstimationTravelTime(), b.getStatus(), 
                b.getPayment().getPaymentStatus(), 
                b.getCust().getName(), 
                b.getDriver().getV().getModel(), 
                b.getBookingDate()
            ));
        }

        ResponseStructure<List<BookingResponseDTO>> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Driver Ride History Fetched");
        rs.setData(historyDTOs);
        return rs;
    }
    
}