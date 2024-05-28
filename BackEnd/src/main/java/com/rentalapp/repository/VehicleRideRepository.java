package com.rentalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.rentalapp.model.VehicleRide;

/*
 * This interface extends JpaRepository to provide CRUD operations for the VehicleRide entity.
 */
public interface VehicleRideRepository extends JpaRepository<VehicleRide, Integer> {
    
    // Retrieves a list of vehicle rides by customer ID
    List<VehicleRide> findByCustomerId(int customerId);
    
    // Retrieves a list of conflicting rides based on start time, end time, and vehicle ID
    @Query("SELECT vr FROM VehicleRide vr WHERE vr.vehicle.id = :vehicleId AND " +
           "(vr.startTime < :endTime AND vr.endTime > :startTime)")
    List<VehicleRide> findConflictingRides(LocalDateTime startTime, LocalDateTime endTime, int vehicleId);

}
