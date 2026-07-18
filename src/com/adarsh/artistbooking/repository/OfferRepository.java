package com.adarsh.artistbooking.repository;

import com.adarsh.artistbooking.entity.Offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferRepository {
    private final Map<Long, Offer> offers;
    private long nextId;

    public OfferRepository() {
        this.offers =  new HashMap<>();
        this.nextId = 1;
    }
    public Offer save(Offer offer)
    {
        if(offer.getId() == 0){
            offer.assignId(nextId++);
        }
        offers.put(offer.getId(), offer);
        return offer ;
    }

    public Offer findById(long id)
    {
        Offer offer = offers.get(id);
        if(offer == null){
            throw new IllegalArgumentException(
                    "Offer not found");
        }

        return offers.get(id);
    }
    public void deleteById(long id)
    {
        validateIsIdPresent(id);
        offers.remove(id);
    }
    public List<Offer> findAll()
    {
        return new ArrayList<>(offers.values());
    }
    private void validateIsIdPresent(long id)
    {
        if(!offers.containsKey(id))
        {
            throw new IllegalArgumentException(String.format("Offer with id %d does not exist", id));
        }
    }
}
