package com.rentalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rentalapp.model.Offers;
import com.rentalapp.service.OfferService;

import java.util.List;

/**
 * RestController for handling offer-related requests.
 * Maps to /offers URL path and provides endpoints to manage offers.
 */
@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    /**
     * Constructor for OfferController, with OfferService autowired.
     * This ensures that OfferService is injected by the Spring framework.
     *
     * @param offerService the service to manage offers
     */
    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * Endpoint to get all offers.
     *
     * @return a list of all offers
     */
    @GetMapping("")
    public List<Offers> getAllOffers() {
        return offerService.getAllOffers();
    }

    /**
     * Endpoint to add a new offer.
     *
     * @param offer the offer to be added, passed in the request body
     */
    @PostMapping("/add")
    public void addOffer(@RequestBody Offers offer) {
        offerService.addOffer(offer);
    }

    /**
     * Endpoint to get an offer by its ID.
     *
     * @param id the ID of the offer
     * @return the offer with the specified ID
     */
    @GetMapping("/{id}")
    public Offers getOfferById(@PathVariable int id) {
        return offerService.getOfferById(id);
    }

    /**
     * Endpoint to update an existing offer.
     *
     * @param id the ID of the offer to update
     * @param offer the updated offer details
     */
    @PutMapping("/update/{id}")
    public void updateOffer(@PathVariable int id, @RequestBody Offers offer) {
        offerService.updateOffer(id, offer);
    }

    /**
     * Endpoint to delete an offer by its ID.
     *
     * @param id the ID of the offer to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deleteOffer(@PathVariable int id) {
        offerService.deleteOffer(id);
    }

    /**
     * Endpoint to get eligible offers based on the number of days.
     *
     * @param days the number of days to determine eligibility
     * @return a list of eligible offers
     */
    @GetMapping("/eligible")
    public List<Offers> getEligibleOffers(@RequestParam int days) {
        return offerService.getEligibleOffers(days);
    }
}
