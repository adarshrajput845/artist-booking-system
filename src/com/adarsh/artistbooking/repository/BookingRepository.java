package com.adarsh.artistbooking.repository;

import com.adarsh.artistbooking.entity.Booking;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepository  {
    private final Map<Long,Booking> bookings;
    private long nextId;

    public BookingRepository() {
        this.bookings = new HashMap<>();
        this.nextId = 1;
    }
    public Booking saveBooking(Booking booking)
    {
        if(booking.getId()==0)
        {
            booking.assignId(nextId++);
        }
        bookings.put(booking.getId(), booking);
        return booking;
    }
    public Booking findById(long id)
    {
        Booking booking = bookings.get(id);
        if(booking == null){
            throw new IllegalArgumentException(
                    "Booking not found");
        }

        return booking ;
    }
    public void deleteById(long id)
    {
        validateIsIdPresent(id);
        bookings.remove(id);
    }
    public List<Booking> findAll()
    {
        return new ArrayList<>(bookings.values());
    }
    private void validateIsIdPresent(long id)
    {
        if(!bookings.containsKey(id))
        {
            throw new IllegalArgumentException(String.format("Booking with id %d does not exist", id));
        }
    }
}

