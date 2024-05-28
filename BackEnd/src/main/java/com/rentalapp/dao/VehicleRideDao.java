package com.rentalapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rentalapp.model.VehicleRide;
import com.rentalapp.repository.VehicleRideRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Access Object (DAO) for handling vehicle ride-related database operations.
 * Uses VehicleRideRepository to perform CRUD operations on VehicleRide entities.
 */
@Component
public class VehicleRideDao {

    private final VehicleRideRepository vehicleRideRepository;

    /**
     * Constructor for VehicleRideDao, with VehicleRideRepository autowired.
     * This ensures that VehicleRideRepository is injected by the Spring framework.
     *
     * @param vehicleRideRepository the repository to manage vehicle rides
     */
    @Autowired
    public VehicleRideDao(VehicleRideRepository vehicleRideRepository) {
        this.vehicleRideRepository = vehicleRideRepository;
    }

    /**
     * Retrieves all rides from the database.
     *
     * @return a list of all vehicle rides
     */
    public List<VehicleRide> getAllRides() {
        return vehicleRideRepository.findAll();
    }

    /**
     * Adds a new ride to the database.
     *
     * @param vehicleRide the vehicle ride to be added
     */
    public void addRide(VehicleRide vehicleRide) {
        vehicleRideRepository.save(vehicleRide);
    }

    /**
     * Retrieves a ride by its ID.
     *
     * @param id the ID of the ride
     * @return the ride with the specified ID, or null if not found
     */
    public VehicleRide getRideById(int id) {
        return vehicleRideRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing ride in the database.
     * If the ride with the specified ID exists, it updates the ride.
     *
     * @param id          the ID of the ride to update
     * @param vehicleRide the updated ride details
     */
    public void updateVehicleRide(int id, VehicleRide vehicleRide) {
        if (vehicleRideRepository.existsById(id)) {
            vehicleRide.setRideId(id); // Assuming the ID is set in the VehicleRide object
            vehicleRideRepository.save(vehicleRide);
        } else {
            // Handle error: Ride not found
            // You can throw an exception or handle it in another way
        }
    }

    /**
     * Cancels a ride by updating its status.
     * Uses @Transactional annotation for transaction management.
     *
     * @param id          the ID of the ride to cancel
     * @param vehicleRide the ride object with updated status
     */
    @Transactional
    public void cancelRide(int id, VehicleRide vehicleRide) {
        VehicleRide existingRide = vehicleRideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        existingRide.setRideStatus(vehicleRide.getRideStatus());

        vehicleRideRepository.save(existingRide);
    }

    /**
     * Deletes a ride from the database by its ID.
     *
     * @param id the ID of the ride to delete
     */
    public void deleteVehicleRide(int id) {
        vehicleRideRepository.deleteById(id);
    }

    /**
     * Retrieves rides associated with a customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of rides associated with the specified customer ID
     */
    public List<VehicleRide> getRidesByCustomerId(int customerId) {
        return vehicleRideRepository.findByCustomerId(customerId);
    }

    /**
     * Finds conflicting rides for a given time period and vehicle ID.
     *
     * @param startTime the start time of the ride
     * @param endTime   the end time of the ride
     * @param vehicleId the ID of the vehicle
     * @return a list of conflicting rides
     */
    public List<VehicleRide> findConflictingRides(LocalDateTime startTime, LocalDateTime endTime, int vehicleId) {
        return vehicleRideRepository.findConflictingRides(startTime, endTime, vehicleId);
    }
}
