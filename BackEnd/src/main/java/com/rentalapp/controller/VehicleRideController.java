package com.rentalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentalapp.model.User;
import com.rentalapp.model.Vehicle;
import com.rentalapp.model.VehicleRide;
import com.rentalapp.model.VehicleRideDTO;
import com.rentalapp.model.VehicleRideResponse;
import com.rentalapp.service.UserService;
import com.rentalapp.service.VehicleRideService;
import com.rentalapp.service.VehicleService;

import java.util.List;

/**
 * RestController for handling vehicle ride-related requests.
 * Maps to /vehiclerides URL path and provides endpoints to manage vehicle rides.
 */
@RestController
@RequestMapping("/vehiclerides")
public class VehicleRideController {

    private final VehicleRideService vehicleRideService;
    private final VehicleService vehicleService;
    private final UserService userService;

    /**
     * Constructor for VehicleRideController, with VehicleRideService, VehicleService, and UserService autowired.
     * This ensures that the necessary services are injected by the Spring framework.
     *
     * @param vehicleRideService the service to manage vehicle rides
     * @param vehicleService the service to manage vehicles
     * @param userService the service to manage users
     */
    @Autowired
    public VehicleRideController(VehicleRideService vehicleRideService, VehicleService vehicleService, UserService userService) {
        this.vehicleRideService = vehicleRideService;
        this.vehicleService = vehicleService;
        this.userService = userService;
    }

    /**
     * Endpoint to get all vehicle rides.
     *
     * @return a list of all vehicle rides
     */
    @GetMapping("/all")
    public List<VehicleRide> getAllVehicleRides() {
        return vehicleRideService.getAllVehicleRides();
    }

    /**
     * Endpoint to get a vehicle ride by its ID.
     *
     * @param id the ID of the vehicle ride
     * @return the vehicle ride with the specified ID
     */
    @GetMapping("/{id}")
    public VehicleRide getVehicleRideById(@PathVariable("id") int id) {
        return vehicleRideService.getVehicleRideById(id);
    }

    /**
     * Endpoint to add a new vehicle ride.
     *
     * @param vehicleRideDTO the data transfer object containing the vehicle ride details
     * @return a response entity with the result of the addition
     */
    @PostMapping("/add")
    public ResponseEntity<VehicleRideResponse> addVehicleRide(@RequestBody VehicleRideDTO vehicleRideDTO) {
        VehicleRideResponse response = vehicleRideService.addVehicleRide(vehicleRideDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to update an existing vehicle ride.
     *
     * @param id the ID of the vehicle ride to update
     * @param vehicleRideDTO the updated vehicle ride details
     */
    @PutMapping("/update/{id}")
    public void updateVehicleRide(@PathVariable("id") int id, @RequestBody VehicleRideDTO vehicleRideDTO) {
        vehicleRideService.updateVehicleRide(id, vehicleRideDTO);
    }

    /**
     * Endpoint to cancel a vehicle ride.
     * Uses HTTP PATCH to partially update the ride status to "Cancelled".
     *
     * @param id the ID of the vehicle ride to cancel
     * @param vehicleRide the vehicle ride object to be updated
     */
    @PatchMapping("/update/{id}")
    public void cancelVehicleRide(@PathVariable("id") int id, @RequestBody VehicleRide vehicleRide) {
        vehicleRide.setRideId(id); // Ensure the rideId is set in the request body
        vehicleRide.setRideStatus("Cancelled"); // Set the ride status to Cancelled
        vehicleRideService.updateVehicleRide(id, vehicleRide);
    }

    /**
     * Endpoint to delete a vehicle ride by its ID.
     *
     * @param id the ID of the vehicle ride to delete
     */
    @DeleteMapping("/{id}")
    public void deleteVehicleRide(@PathVariable("id") int id) {
        vehicleRideService.deleteVehicleRide(id);
    }

    /**
     * Endpoint to get vehicle rides by customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of vehicle rides associated with the specified customer ID
     */
    @GetMapping("/customer/{customerId}")
    public List<VehicleRide> getRidesByCustomerId(@PathVariable("customerId") int customerId) {
        return vehicleRideService.getRidesByCustomerId(customerId);
    }
}
