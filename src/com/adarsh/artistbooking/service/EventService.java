package com.adarsh.artistbooking.service;

import com.adarsh.artistbooking.entity.Event;
import com.adarsh.artistbooking.entity.Money;
import com.adarsh.artistbooking.entity.Offer;
import com.adarsh.artistbooking.entity.Slot;
import com.adarsh.artistbooking.enums.SlotStatus;
import com.adarsh.artistbooking.repository.EventRepository;
import com.adarsh.artistbooking.repository.OfferRepository;
import com.adarsh.artistbooking.repository.SlotRepository;

import java.time.LocalDateTime;

public class EventService {
    private final EventRepository eventRepository;
    private final OfferRepository offerRepository;
    private final SlotRepository slotRepository;

    public EventService(EventRepository eventRepository, OfferRepository offerRepository, SlotRepository slotRepository) {
        this.eventRepository = eventRepository;
        this.offerRepository = offerRepository;
        this.slotRepository = slotRepository;
    }

    public Offer sendOffer(
            long eventId,
            long artistId,
            long slotId,
            Money proposedAmount,
            LocalDateTime expiryTime
    ) {
        Event event = eventRepository.findById(eventId);
        Slot slot = slotRepository.findById(slotId);
        if(slot.getArtistId() != artistId){
            throw new IllegalArgumentException(
                    "Slot does not belong to the selected artist.");
        }
        if(slot.getStatus() != SlotStatus.AVAILABLE){
            throw new IllegalStateException(
                    "Cannot create offer for unavailable slot.");
        }
        Offer offer= new  Offer(eventId, slotId, proposedAmount, expiryTime);
        event.addOffer(offer);
        offerRepository.saveOffer(offer);
        eventRepository.saveEvent(event);

        return offer;
    }
    public void renameEvent(long eventId, String newName)
    {
        Event event = eventRepository.findById(eventId);
        event.rename(newName);
        eventRepository.saveEvent(event);
    }
    public Event createEvent(long userId,String eventName, LocalDateTime eventDate, String venue)
    {
        Event event = new Event(userId, eventName, eventDate, venue);
        eventRepository.saveEvent(event);
        return event;
    }
    public void cancelEvent(long eventId)
    {
        Event event = eventRepository.findById(eventId);
        event.cancelEvent();
        eventRepository.saveEvent(event);
    }
}
