package com.alpha.RideX.Entity;

public enum PaymentStatus {
	PENDING,      // Created, money not deducted yet
    SUCCESS,      // Money received
    FAILED,       // Transaction declined
    REFUNDED      // (Optional) If you cancel later
}
