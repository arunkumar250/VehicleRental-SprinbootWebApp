package com.rentalapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.model.Offers;
/*
 * This interface extends JpaRepository to provide CRUD operations for the Offers entity.
 * Additionally, it defines a custom query method findByMinimumDaysLessThanEqual, which fetches offers 
 * where the minimum days are less than or equal to the specified number of days.
 */
public interface OfferRepository extends JpaRepository<Offers, Integer> {
    List<Offers> findByMinimumDaysLessThanEqual(long days);
}
