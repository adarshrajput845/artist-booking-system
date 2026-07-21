package com.adarsh.artistbooking.repository;

import com.adarsh.artistbooking.entity.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlotRepository {
    private final Map<Long, Slot> slots;
    private long nextId;

    public SlotRepository() {
        this.slots = new HashMap<>();
        this.nextId = 1;
    }
    public Slot saveSlot(Slot slot)
    {
        if(slot.getId()==0)
        {
            slot.assignId(nextId++);
        }
        slots.put(slot.getId(), slot);
        return slot;
    }
    public Slot findById(long id)
    {
        Slot slot = slots.get(id);
        if(slot == null){
            throw new IllegalArgumentException(
                    "Booking not found");
        }

        return slot ;
    }
    public void deleteById(long id)
    {
        validateIsIdPresent(id);
        slots.remove(id);
    }
    public List<Slot> findAll()
    {
        return new ArrayList<>(slots.values());
    }
    private void validateIsIdPresent(long id)
    {
        if(!slots.containsKey(id))
        {
            throw new IllegalArgumentException(String.format("Slot with id %d does not exist", id));
        }
    }
}
