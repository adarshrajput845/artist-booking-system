package com.adarsh.artistbooking;

import com.adarsh.artistbooking.entity.*;
import com.adarsh.artistbooking.enums.Category;
import com.adarsh.artistbooking.repository.*;
import com.adarsh.artistbooking.service.*;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        // ===========================
        // Create Repositories
        // ===========================
        ArtistRepository artistRepository = new ArtistRepository();
        SlotRepository slotRepository = new SlotRepository();
        EventRepository eventRepository = new EventRepository();
        OfferRepository offerRepository = new OfferRepository();
        BookingRepository bookingRepository = new BookingRepository();

        // ===========================
        // Create Services
        // ===========================
        EventService eventService = new EventService(
                eventRepository,
                offerRepository,
                slotRepository
        );

        OfferService offerService = new OfferService(
                offerRepository,
                slotRepository,
                bookingRepository
        );

        BookingService bookingService = new BookingService(
                bookingRepository,
                offerRepository,
                slotRepository
        );

        // ===========================
        // Create Artist
        // ===========================
        Artist artist = new Artist(
                "Arijit Singh",
                Category.SINGER,
                "Mumbai",
                15,
                new Money(50000)
        );

        artistRepository.saveArtist(artist);

        System.out.println("✅ Artist Created : " + artist.getName());

        // ===========================
        // Create Slot
        // ===========================
        Slot slot = new Slot(
                artist.getId(),
                LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(10).plusHours(3)
        );

        slotRepository.saveSlot(slot);

        System.out.println("✅ Slot Created");

        // ===========================
        // Create Event
        // ===========================
        Event event = eventService.createEvent(
                101L,
                "NSUT Cultural Fest",
                LocalDateTime.now().plusDays(10),
                "Delhi"
        );

        System.out.println("✅ Event Created");

        // ===========================
        // User sends Offer
        // ===========================
        Offer offer = eventService.sendOffer(
                event.getId(),
                artist.getId(),
                slot.getId(),
                new Money(45000),
                LocalDateTime.now().plusDays(7)
        );

        System.out.println("✅ Offer Sent");

        // ===========================
        // Artist accepts Offer
        // Booking gets created
        // Slot becomes RESERVED
        // ===========================
        Booking booking = offerService.acceptOffer(offer.getId());

        System.out.println("✅ Offer Accepted");

        // ===========================
        // User pays advance
        // Slot becomes BOOKED
        // ===========================
        bookingService.confirmAdvancePayment(
                booking.getId()
        );

        System.out.println("✅ Advance Payment Received");

        // ===========================
        // Event completed
        // ===========================
        bookingService.completeBooking(
                booking.getId()
        );

        System.out.println("✅ Booking Completed");

        // ===========================
        // Final Status
        // ===========================
        System.out.println();
        System.out.println("========== FINAL STATUS ==========");
        System.out.println("Offer Status   : " + offer.getStatus());
        System.out.println("Booking Status : " + booking.getStatus());
        System.out.println("Payment Status : " + booking.getPaymentStatus());
        System.out.println("Slot Status    : " + slot.getStatus());
    }
}
