/*
 * This class represents the service layer for Offers.
 * It contains methods to interact with offers, such as retrieving, adding, updating, and deleting offers.
 */
package com.rentalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalapp.dao.OfferDao;
import com.rentalapp.model.Offers;
import com.rentalapp.repository.OfferRepository;

import java.util.List;

@Service
public class OfferService {

    private final OfferDao offerDao;
    private OfferRepository offerRepository;

    /*
     * Constructs an OfferService with the provided OfferDao and OfferRepository.
     * @param offerDao The data access object for Offers.
     * @param offerRepository The repository for Offers.
     */
    @Autowired
    public OfferService(OfferDao offerDao, OfferRepository offerRepository) {
        this.offerDao = offerDao;
        this.offerRepository = offerRepository;
    }

    /*
     * Retrieves all offers from the database.
     * @return A list of all offers.
     */
    public List<Offers> getAllOffers() {
        return offerDao.getAllOffers();
    }

    /*
     * Retrieves an offer by its ID from the database.
     * @param id The ID of the offer to retrieve.
     * @return The offer with the specified ID, or null if not found.
     */
    public Offers getOfferById(int id) {
        return offerDao.getOfferById(id);
    }

    /*
     * Adds a new offer to the database.
     * @param offer The offer to add.
     */
    public void addOffer(Offers offer) {
        offerDao.addOffer(offer);
    }

    /*
     * Updates an existing offer in the database.
     * @param id The ID of the offer to update.
     * @param updatedOffer The updated offer object.
     */
    public void updateOffer(int id, Offers updatedOffer) {
        offerDao.updateOffer(id, updatedOffer);
    }

    /*
     * Deletes an offer from the database by its ID.
     * @param id The ID of the offer to delete.
     */
    public void deleteOffer(int id) {
        offerDao.deleteOffer(id);
    }
    
    /*
     * Retrieves offers eligible based on the number of days.
     * @param days The number of days for eligibility.
     * @return A list of eligible offers.
     */
    public List<Offers> getEligibleOffers(int days) {
        return offerRepository.findByMinimumDaysLessThanEqual(days);
    }
}
