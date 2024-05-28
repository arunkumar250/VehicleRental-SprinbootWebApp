/*
 * This class represents the service layer for managing vehicles.
 * It contains methods to retrieve, add, update, and delete vehicles.
 */
package com.rentalapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rentalapp.dao.VehicleDao;
import com.rentalapp.model.Vehicle;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleDao vehicleDao;

    /*
     * Constructs a VehicleService with the provided dependency.
     * @param vehicleDao The data access object for vehicles.
     */
    @Autowired
    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    /*
     * Retrieves all vehicles from the database.
     * @return A list of all vehicles.
     */
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }

    /*
     * Adds a new vehicle to the database.
     * @param vehicle The vehicle object to add.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
    }

    /*
     * Retrieves a vehicle by ID from the database.
     * @param id The ID of the vehicle to retrieve.
     * @return The vehicle with the specified ID, or null if not found.
     */
    public Vehicle getVehicleById(int id) {
        return vehicleDao.getVehicleById(id);
    }

    /*
     * Updates an existing vehicle in the database.
     * @param id The ID of the vehicle to update.
     * @param vehicle The updated vehicle object.
     * @return The updated vehicle object.
     */
    public Vehicle updateVehicle(int id, Vehicle vehicle) {
        return vehicleDao.updateVehicle(id, vehicle);
    }

    /*
     * Deletes a vehicle from the database.
     * @param id The ID of the vehicle to delete.
     * @return true if the vehicle was successfully deleted, otherwise false.
     */
    public boolean deleteVehicle(int id) {
        return vehicleDao.deleteVehicle(id);
    }
}
