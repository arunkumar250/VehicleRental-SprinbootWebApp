package com.rentalapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing Vehicles.
 */
@Entity
@Table(name = "Vehicles")
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
	public int id;
    
    @Column(name = "vehicle_type")
    private String type;
    
    @Column(name = "vehicle_name")
    private String name;
    
    @Column(name = "registration_no")
    private String registrationNumber;
    
    @Column(name = "fuel_type")
    private String fuelType;

	@Column(name = "rate")
    private int rate;
    
    @Column(name = "color")
    private String vehicleColor;
    
    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

	public Vehicle() {
    }
    
    public Vehicle(String type, String name, String registrationNumber, String fuelType, int rate, String vehicleColor) {
        this.type = type;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.fuelType = fuelType;
        this.rate = rate;
        this.vehicleColor = vehicleColor;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", name=" + name + ", registrationNumber=" + registrationNumber
				+ ", fuelType=" + fuelType + ", rate=" + rate + ", vehicleColor=" + vehicleColor + "]";
	}
}
