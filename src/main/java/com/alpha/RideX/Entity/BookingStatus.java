package com.alpha.RideX.Entity;


public enum BookingStatus {
    SEARCHING,    
    CONFIRMED,    // Driver assigned, car locked
    IN_TRIP,      // Customer is in the car
    COMPLETED,    // Ride finished
    CANCELLED     // Ride cancelled by user/driver
}
