/*
 * This class represents the service layer for managing vehicle rides.
 * It contains methods to retrieve, add, update, and delete vehicle rides.
 */
package com.rentalapp.service;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalapp.dao.VehicleRideDao;
import com.rentalapp.model.Offers;
import com.rentalapp.model.User;
import com.rentalapp.model.Vehicle;
import com.rentalapp.model.VehicleRide;
import com.rentalapp.model.VehicleRideDTO;
import com.rentalapp.model.VehicleRideResponse;
import com.rentalapp.repository.UserRepository;
import com.rentalapp.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleRideService {

    private final VehicleRideDao vehicleRideDAO;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final OfferService offerService;

    /*
     * Constructs a VehicleRideService with the provided dependencies.
     * @param vehicleRideDAO The data access object for vehicle rides.
     * @param vehicleRepository The repository for vehicles.
     * @param userRepository The repository for users.
     * @param offerService The service for offers.
     */
    @Autowired
    public VehicleRideService(VehicleRideDao vehicleRideDAO, VehicleRepository vehicleRepository, UserRepository userRepository, OfferService offerService) {
        this.vehicleRideDAO = vehicleRideDAO;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.offerService = offerService;
    }

    /*
     * Retrieves all vehicle rides from the database.
     * @return A list of all vehicle rides.
     */
    public List<VehicleRide> getAllVehicleRides() {
        return vehicleRideDAO.getAllRides();
    }

    /*
     * Retrieves a vehicle ride by ID from the database.
     * @param id The ID of the vehicle ride to retrieve.
     * @return The vehicle ride with the specified ID, or null if not found.
     */
    public VehicleRide getVehicleRideById(int id) {
        return vehicleRideDAO.getRideById(id);
    }

    /*
     * Adds a new vehicle ride to the database.
     * @param vehicleRideDTO The DTO containing information about the vehicle ride.
     * @return A response object containing details about the added ride.
     */
    public VehicleRideResponse addVehicleRide(VehicleRideDTO vehicleRideDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleRideDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        User customer = userRepository.findById(vehicleRideDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<VehicleRide> conflictingRides = vehicleRideDAO.findConflictingRides(
                vehicleRideDTO.getStartTime(), vehicleRideDTO.getEndTime(), vehicle.getId());
        if (!conflictingRides.isEmpty()) {
            throw new RuntimeException("Vehicle is not available at the requested time");
        }

        long daysDifference = ChronoUnit.DAYS.between(vehicleRideDTO.getStartTime(), vehicleRideDTO.getEndTime());

        List<Offers> eligibleOffers = offerService.getEligibleOffers((int) daysDifference);

        int actualAmount = vehicleRideDTO.getTotalAmount();
        int savedAmount = 0;
        int totalAmount = actualAmount;

        if (!eligibleOffers.isEmpty()) {
            Offers bestOffer = eligibleOffers.get(0);
            int discountValue = bestOffer.getDiscountValue();
            savedAmount = actualAmount * discountValue / 100;
            totalAmount = actualAmount - savedAmount;
        }

        VehicleRide vehicleRide = new VehicleRide();
        vehicleRide.setVehicle(vehicle);
        vehicleRide.setCustomer(customer);
        vehicleRide.setStartTime(vehicleRideDTO.getStartTime());
        vehicleRide.setEndTime(vehicleRideDTO.getEndTime());
        vehicleRide.setCreateDateTime(vehicleRideDTO.getCreatedDateTime());
        vehicleRide.setRideStatus(vehicleRideDTO.getRideStatus());
        vehicleRide.setTotalAmount(totalAmount);

        vehicleRideDAO.addRide(vehicleRide);

        return new VehicleRideResponse("Vehicle ride added successfully", actualAmount, totalAmount, savedAmount);
    }

    /*
     * Updates an existing vehicle ride in the database.
     * @param id The ID of the vehicle ride to update.
     * @param vehicleRideDTO The DTO containing updated information about the vehicle ride.
     */
    public void updateVehicleRide(int id, VehicleRideDTO vehicleRideDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleRideDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        User customer = userRepository.findById(vehicleRideDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        VehicleRide vehicleRide = new VehicleRide();
        vehicleRide.setRideId(id);
        vehicleRide.setVehicle(vehicle);
        vehicleRide.setCustomer(customer);
        vehicleRide.setStartTime(vehicleRideDTO.getStartTime());
        vehicleRide.setEndTime(vehicleRideDTO.getEndTime());
        vehicleRide.setCreateDateTime(vehicleRideDTO.getCreatedDateTime());
        vehicleRide.setRideStatus(vehicleRideDTO.getRideStatus());
        vehicleRide.setTotalAmount(vehicleRideDTO.getTotalAmount());

        vehicleRideDAO.updateVehicleRide(id, vehicleRide);
    }

    /*
     * Cancels an existing vehicle ride in the database.
     * @param id The ID of the vehicle ride to cancel.
     * @param vehicleRide The updated vehicle ride object.
     */
    public void updateVehicleRide(int id, VehicleRide vehicleRide) {
        vehicleRide.setRideId(id); // Ensure the rideId is set
        vehicleRideDAO.cancelRide(id, vehicleRide);
    }

    /*
     * Deletes a vehicle ride from the database.
     * @param id The ID of the vehicle ride to delete.
     */
    public void deleteVehicleRide(int id) {
        vehicleRideDAO.deleteVehicleRide(id);
    }

    /*
     * Retrieves all vehicle rides associated with a particular customer from the database.
     * @param customerId The ID of the customer.
     * @return A list of all vehicle rides associated with the specified customer.
     */
    public List<VehicleRide> getRidesByCustomerId(int customerId) {
        return vehicleRideDAO.getRidesByCustomerId(customerId);
    }
}
