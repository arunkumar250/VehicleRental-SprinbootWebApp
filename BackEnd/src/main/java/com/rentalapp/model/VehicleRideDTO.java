package com.rentalapp.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) for VehicleRide.
 */
public class VehicleRideDTO {
    private int vehicleId;
    private int customerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdDateTime;
    private String rideStatus;
    private int totalAmount;

    // Getters and Setters

    @JsonProperty("vehicleId")
    public int getVehicleId() {
        return vehicleId;
    }

    @JsonProperty("vehicleId")
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("customerId")
    public int getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("startTime")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("endTime")
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("createdDateTime")
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    @JsonProperty("createdDateTime")
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @JsonProperty("rideStatus")
    public String getRideStatus() {
        return rideStatus;
    }

    @JsonProperty("rideStatus")
    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    @JsonProperty("totalAmount")
    public int getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("totalAmount")
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "VehicleRideDTO [vehicleId=" + vehicleId + ", customerId=" + customerId + ", startTime=" + startTime
                + ", endTime=" + endTime + ", createdDateTime=" + createdDateTime + ", rideStatus=" + rideStatus
                + ", totalAmount=" + totalAmount + "]";
    }
}
