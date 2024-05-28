package com.rentalapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Entity class representing Vehicle Rides.
 */
@Entity
@Table(name = "Vehicle_Rides")
public class VehicleRide {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private int rideId;
    
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    
    @Column(name = "created_datetime")
    private LocalDateTime createdDateTime;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    /**
     * Getter for createdDateTime.
     *
     * @return createdDateTime LocalDateTime object representing the created date and time of the ride
     */
    public LocalDateTime getCreateDateTime() {
		return createdDateTime;
	}

    /**
     * Setter for createdDateTime.
     *
     * @param createDateTime LocalDateTime object representing the created date and time of the ride
     */
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createdDateTime = createDateTime;
	}

	@Column(name = "end_time")
    private LocalDateTime endTime;
    
    
    @Column(name = "ride_status")
    private String rideStatus;
    
    @Column(name = "total_amount")
    private int totalAmount;
    
    // Constructors, getters, and setters
    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    @Override
	public String toString() {
		return "VehicleRide [rideId=" + rideId + ", vehicle=" + vehicle + ", customer=" + customer + ", createDateTime="
				+ createdDateTime + ", startTime=" + startTime + ", endTime=" + endTime + ", rideStatus=" + rideStatus
				+ ", totalAmount=" + totalAmount + "]";
	}

	public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
