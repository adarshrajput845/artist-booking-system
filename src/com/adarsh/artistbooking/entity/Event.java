package com.adarsh.artistbooking.entity;

import com.adarsh.artistbooking.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event {
    private long id;
    private long userId;
    private String eventName;
    private LocalDateTime eventDate;
    private String venue;
    private EventStatus status;
    private List<Offer> offers;

    public Event(long userId,String eventName, LocalDateTime eventDate, String venue) {
        this.id = 0;
        this.userId = userId;
        validateName(eventName);
        this.eventName = eventName;
        validateEventDate(eventDate);
        this.eventDate = eventDate;
        validateEventVenue(venue);
        this.venue = venue;
        this.status= EventStatus.Planning;
        this.offers = new ArrayList<>();
    }

    public String getEventName() {
        return eventName;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void rename(String eventName) {
        validateName(eventName);
        this.eventName = eventName;
    }

    public List<Offer> getOffers() {
        return Collections.unmodifiableList(offers);
    }

    public void addOffer(Offer offer)
    {
        validateOffer(offer);
        offers.add(offer);
        // offer.eventid= id;
    }
    public void removeOffer(Offer offer){
        offers.remove(offer);
    }
    // validations
    private void validateName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Event Name cannot be null or empty");
        }
        if(name.length() > 100)
        {
            throw new IllegalArgumentException("Event Name cannot be longer than 100 characters");
        }
    }
    private void validateEventDate(LocalDateTime date)
    {
        if (date == null)
        {
            throw new IllegalArgumentException("Event Date cannot be null");
        }
        if(date.isBefore(LocalDateTime.now()))
        {
            throw new IllegalArgumentException("Event Date cannot be before Now");
        }
    }
    private void validateEventVenue(String venue)
    {
        if (venue == null || venue.trim().isEmpty())
        {
            throw new IllegalArgumentException("Venue cannot be null or empty");
        }
    }

    private void validateOffer(Offer offer)
    {
        if (offer == null)
        {
            throw new IllegalArgumentException("Offer cannot be null");
        }
    }
}
