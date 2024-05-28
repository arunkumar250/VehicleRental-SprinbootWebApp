package com.rentalapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentalapp.model.Vehicle;
import com.rentalapp.service.VehicleService;

/**
 * RestController for handling vehicle-related requests.
 * Maps to /vehicles URL path and provides endpoints to manage vehicles.
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    /**
     * Dependency on VehicleService to handle business logic for vehicles.
     */
    @Autowired
    VehicleService vehicleService;

    /**
     * Endpoint to get all vehicles.
     *
     * @return a list of all vehicles
     */
    @GetMapping("")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    /**
     * Endpoint to add a new vehicle.
     *
     * @param vehicle the vehicle to be added, passed in the request body
     */
    @PostMapping("/add")
    public void addVehicle(@RequestBody Vehicle vehicle) {
    	System.out.println(vehicle.toString());
        vehicleService.addVehicle(vehicle);
    }

    /**
     * Endpoint to get a vehicle by its ID.
     *
     * @param id the ID of the vehicle
     * @return the vehicle with the specified ID
     */
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable int id) {
        return vehicleService.getVehicleById(id);
    }

    /**
     * Endpoint to update an existing vehicle.
     *
     * @param id the ID of the vehicle to update
     * @param vehicle the updated vehicle details
     * @return the updated vehicle
     */
    @PutMapping("/update/{id}")
    public Vehicle updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    /**
     * Endpoint to delete a vehicle by its ID.
     *
     * @param id the ID of the vehicle to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
    }
   
}
