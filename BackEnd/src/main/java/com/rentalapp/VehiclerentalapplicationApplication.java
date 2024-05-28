package com.rentalapp;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 

/**
 * Main application class for the Vehicle Rental Application.
 * This class is the entry point of the Spring Boot application.
 * It initializes and runs the application.
 */
@SpringBootApplication 
public class VehiclerentalapplicationApplication { 
    public static void main(String[] args) { 
        // SpringApplication.run method starts the entire Spring Boot application
        SpringApplication.run(VehiclerentalapplicationApplication.class, args);
    }

}
