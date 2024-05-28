/*
 * This interface extends JpaRepository to provide CRUD operations for the Vehicle entity.
 */
package com.rentalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
	
}
