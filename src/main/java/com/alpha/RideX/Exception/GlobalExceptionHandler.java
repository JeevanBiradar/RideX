package com.alpha.RideX.Exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.RideX.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Customer Not Found
    @ExceptionHandler(CustomerNotFoundWithMobile.class)
    public ResponseEntity<ResponseStructure<String>> handleCustomerNotFound(CustomerNotFoundWithMobile ex) {
        ResponseStructure<String> structure = new ResponseStructure<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

    // Customer Already Exists
    @ExceptionHandler(CustomeralreadyExists.class)
    public ResponseEntity<ResponseStructure<String>> handleCustomerExists(CustomeralreadyExists ex) {
        ResponseStructure<String> structure = new ResponseStructure<>(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(structure, HttpStatus.CONFLICT);
    }

    // Driver Not Found
    @ExceptionHandler(DriverNOtFoundWiththismobileNO.class)
    public ResponseEntity<ResponseStructure<String>> handleDriverNotFound(DriverNOtFoundWiththismobileNO ex) {
        ResponseStructure<String> structure = new ResponseStructure<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

    // Vehicles Not Available To Destination
    @ExceptionHandler(VehiclesareNotavilabletoDestinationLocation.class)
    public ResponseEntity<ResponseStructure<String>> handleVehicleNotAvailable(VehiclesareNotavilabletoDestinationLocation ex) {
        ResponseStructure<String> structure = new ResponseStructure<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

    // Global — any unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseStructure<String>> handleGlobal(Exception ex) {
        ResponseStructure<String> structure = new ResponseStructure<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

