package com.rentalapp.model;

/**
 * Response model for a VehicleRide operation.
 */
public class VehicleRideResponse {
    private String message;
    private int actualAmount;
    private int totalAmount;
    private int savedAmount;

    /**
     * Constructs a VehicleRideResponse object with the specified parameters.
     *
     * @param message      The message associated with the response.
     * @param actualAmount The actual amount paid for the ride.
     * @param totalAmount  The total amount for the ride.
     * @param savedAmount  The amount saved in the ride operation.
     */
    public VehicleRideResponse(String message, int actualAmount, int totalAmount, int savedAmount) {
        this.message = message;
        this.actualAmount = actualAmount;
        this.totalAmount = totalAmount;
        this.savedAmount = savedAmount;
    }

    // Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(int savedAmount) {
        this.savedAmount = savedAmount;
    }
}
