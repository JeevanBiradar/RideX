package com.alpha.RideX.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.RideX.ResponseStructure;
import com.alpha.RideX.DTO.BookingRequestDTO;
import com.alpha.RideX.DTO.BookingResponseDTO;
import com.alpha.RideX.Service.BookingService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<ResponseStructure<BookingResponseDTO>> bookRide(@RequestBody BookingRequestDTO request) {
        
        ResponseStructure<BookingResponseDTO> response = bookingService.createBooking(request);
        
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
    
 

    // COMPELETE BOOKING
    @PostMapping("/completebooking")
    public ResponseEntity<ResponseStructure<String>> completeRide(@RequestParam int bookingId) {
        ResponseStructure<String> response = bookingService.completeRide(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // CUSTOMER BOOKING HISTORY
    @GetMapping("/history/customer")
    public ResponseEntity<ResponseStructure<List<BookingResponseDTO>>> getCustomerHistory(@RequestParam long mobileNo) {
        ResponseStructure<List<BookingResponseDTO>> response = bookingService.getBookingHistoryByCustomer(mobileNo);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // DRIVER BOOKING HISTORY
    @GetMapping("/history/driver")
    public ResponseEntity<ResponseStructure<List<BookingResponseDTO>>> getDriverHistory(@RequestParam long mobileNo) {
        ResponseStructure<List<BookingResponseDTO>> response = bookingService.getBookingHistoryByDriver(mobileNo);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}