package com.adarsh.artistbooking.service;

import com.adarsh.artistbooking.entity.Booking;
import com.adarsh.artistbooking.entity.Offer;
import com.adarsh.artistbooking.entity.Slot;
import com.adarsh.artistbooking.repository.BookingRepository;
import com.adarsh.artistbooking.repository.OfferRepository;
import com.adarsh.artistbooking.repository.SlotRepository;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final OfferRepository offerRepository;
    private final SlotRepository slotRepository;

    public BookingService(BookingRepository bookingRepository, OfferRepository offerRepository, SlotRepository slotRepository) {
        this.bookingRepository = bookingRepository;
        this.offerRepository = offerRepository;
        this.slotRepository = slotRepository;
    }
    public void confirmAdvancePayment(long id){
        Booking booking = bookingRepository.findById(id);

        Offer offer = offerRepository.findById(booking.getOfferId());

        Slot slot = slotRepository.findById(offer.getSlotId());

        slot.markBooked();

        booking.confirmPayment();

        slotRepository.saveSlot(slot);

        bookingRepository.saveBooking(booking);
    }
    public void cancelBooking(long id){
        Booking booking = bookingRepository.findById(id);

        Offer offer = offerRepository.findById(booking.getOfferId());

        Slot slot = slotRepository.findById(offer.getSlotId());

        booking.cancel();

        slot.markAvailable();

        bookingRepository.saveBooking(booking);

        slotRepository.saveSlot(slot);
    }
    public void completeBooking(long id){
        Booking booking = bookingRepository.findById(id);

        booking.complete();

        bookingRepository.saveBooking(booking);
    }

}
