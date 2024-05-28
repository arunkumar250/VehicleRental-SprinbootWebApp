package com.rentalapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rentalapp.model.Offers;
import com.rentalapp.repository.OfferRepository;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object (DAO) for handling offer-related database operations.
 * Uses OfferRepository to perform CRUD operations on Offers.
 */
@Repository
public class OfferDao {

    private final OfferRepository offerRepository;

    /**
     * Constructor for OfferDao, with OfferRepository autowired.
     * This ensures that OfferRepository is injected by the Spring framework.
     *
     * @param offerRepository the repository to manage offers
     */
    @Autowired
    public OfferDao(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    /**
     * Retrieves all offers from the database.
     *
     * @return a list of all offers
     */
    public List<Offers> getAllOffers() {
        return offerRepository.findAll();
    }

    /**
     * Retrieves an offer by its ID.
     *
     * @param id the ID of the offer
     * @return the offer with the specified ID, or null if not found
     */
    public Offers getOfferById(int id) {
        Optional<Offers> optionalOffer = offerRepository.findById(id);
        return optionalOffer.orElse(null);
    }

    /**
     * Adds a new offer to the database.
     *
     * @param offer the offer to be added
     */
    public void addOffer(Offers offer) {
        offerRepository.save(offer);
    }

    /**
     * Updates an existing offer in the database.
     * If the offer with the specified ID exists, it updates the offer.
     *
     * @param id the ID of the offer to update
     * @param updatedOffer the updated offer details
     */
    public void updateOffer(int id, Offers updatedOffer) {
        if (offerRepository.existsById(id)) {
            updatedOffer.setId(id);
            offerRepository.save(updatedOffer);
        }
    }

    /**
     * Deletes an offer from the database by its ID.
     *
     * @param id the ID of the offer to delete
     */
    public void deleteOffer(int id) {
        offerRepository.deleteById(id);
    }
}
