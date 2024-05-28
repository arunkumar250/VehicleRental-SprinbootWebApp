package com.rentalapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentalapp.model.Vehicle;
import com.rentalapp.repository.VehicleRepository;

import java.util.List;

/**
 * Data Access Object (DAO) for handling vehicle-related database operations.
 * Uses VehicleRepository to perform CRUD operations on Vehicle entities.
 */
@Component
public class VehicleDao {

    private final VehicleRepository vehicleRepository;

    /**
     * Constructor for VehicleDao, with VehicleRepository autowired.
     * This ensures that VehicleRepository is injected by the Spring framework.
     *
     * @param vehicleRepository the repository to manage vehicles
     */
    @Autowired
    public VehicleDao(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Retrieves all vehicles from the database.
     *
     * @return a list of all vehicles
     */
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    /**
     * Adds a new vehicle to the database.
     *
     * @param vehicle the vehicle to be added
     */
    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param id the ID of the vehicle
     * @return the vehicle with the specified ID, or null if not found
     */
    public Vehicle getVehicleById(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing vehicle in the database.
     * If the vehicle with the specified ID exists, it updates the vehicle.
     * @param id the ID of the vehicle to update
     * @param updatedVehicle the updated vehicle details
     * @return the updated vehicle, or null if the vehicle does not exist
     */
    public Vehicle updateVehicle(int id, Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle != null) {
            vehicle.setName(updatedVehicle.getName());
            vehicle.setType(updatedVehicle.getType());
            vehicle.setRate(updatedVehicle.getRate());
            vehicle.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
            vehicle.setFuelType(updatedVehicle.getFuelType());
            vehicle.setVehicleColor(updatedVehicle.getVehicleColor());
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    /**
     * Deletes a vehicle from the database by its ID.
     *
     * @param id the ID of the vehicle to delete
     * @return true if the vehicle was deleted successfully, false otherwise
     */
    public boolean deleteVehicle(int id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
