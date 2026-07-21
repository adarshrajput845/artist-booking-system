package com.adarsh.artistbooking.repository;

import com.adarsh.artistbooking.entity.Event;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepository {
    private final Map<Long, Event> events;
    private long nextId;

    public EventRepository() {
        this.events =  new HashMap<>();
        this.nextId = 1;
    }
    public Event saveEvent(Event event)
    {
        if(event.getId() == 0){
            event.assignId(nextId++);
        }
        events.put(event.getId(), event);
        return event ;
    }

    public Event findById(long id)
    {
        Event event = events.get(id);
        if(event == null){
            throw new IllegalArgumentException(
                    "Event not found");
        }

        return events.get(id);
    }
    public void deleteById(long id)
    {
        validateIsIdPresent(id);
        events.remove(id);
    }
    public List<Event> findAll()
    {
        return new ArrayList<>(events.values());
    }
    private void validateIsIdPresent(long id)
    {
        if(!events.containsKey(id))
        {
            throw new IllegalArgumentException(String.format("Event with id %d does not exist", id));
        }
    }
}

