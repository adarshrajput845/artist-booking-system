package com.adarsh.artistbooking.service;

import com.adarsh.artistbooking.entity.Booking;
import com.adarsh.artistbooking.entity.Offer;
import com.adarsh.artistbooking.entity.Slot;
import com.adarsh.artistbooking.repository.BookingRepository;
import com.adarsh.artistbooking.repository.OfferRepository;
import com.adarsh.artistbooking.repository.SlotRepository;

public class OfferService {
    private final OfferRepository offerRepository;
    private final SlotRepository slotRepository;
    private final BookingRepository bookingRepository;

    public OfferService(OfferRepository offerRepository,
                        SlotRepository slotRepository,
                        BookingRepository bookingRepository) {

        this.offerRepository = offerRepository;
        this.slotRepository = slotRepository;
        this.bookingRepository = bookingRepository;
    }


    public Booking acceptOffer(long offerId){
        Offer offer = offerRepository.findById(offerId);
        Slot slot = slotRepository.findById(offer.getSlotId());

        slot.markReserved();
        offer.accept();

        Booking booking = new Booking(offerId,offer.getProposedAmount());
        offerRepository.saveOffer(offer);

        slotRepository.saveSlot(slot);

        bookingRepository.saveBooking(booking);
        System.out.println(
                "Booking created successfully. Waiting for advance payment."
        );
        return booking;
    }

    public void rejectOffer(long offerId){
        Offer offer = offerRepository.findById(offerId);
        offer.reject();
        offerRepository.saveOffer(offer);
    }
    public void cancelOffer(long offerId){
        Offer offer = offerRepository.findById(offerId);
        offer.cancel();
        offerRepository.saveOffer(offer);
    }
}
