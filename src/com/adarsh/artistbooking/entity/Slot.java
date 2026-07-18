package com.adarsh.artistbooking.entity;

import com.adarsh.artistbooking.enums.SlotStatus;

import java.time.LocalDateTime;

public class Slot {

    private long id;
    private long artistId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatus status;


    public Slot(long id, long artistId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.artistId = artistId;
        validateTime(startTime);
        validateTime(endTime);
        validateEndTime(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = SlotStatus.AVAILABLE;

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public SlotStatus getStatus(){
        return status;
    }
    public boolean overlapsWith(Slot other){
        return this.startTime.isBefore(other.endTime)
                && other.startTime.isBefore(this.endTime);

    }

    public void markBooked(){
        validateAvailability(this.status);
        this.status = SlotStatus.BOOKED;
    }
    public void markAvailable(){
        this.status = SlotStatus.AVAILABLE;
    }
    public void markUnavailable(){
        validateAvailability(this.status);
        this.status = SlotStatus.UNAVAILABLE;
    }
    public void delete(){
        validateBookedstatus(this.status);
        this.status = SlotStatus.DELETED;

    }

    public void updateTime(LocalDateTime newStart, LocalDateTime newEnd){
        validateBookedstatus(this.status);
        validateTime(newStart);
        validateTime(newEnd);
        validateEndTime(newStart, newEnd);
        this.startTime = newStart;
        this.endTime = newEnd;
    }


    private void validateAvailability(SlotStatus status){
        if(status != SlotStatus.AVAILABLE)
            {
            throw new IllegalStateException("Only changes allowed if Slot is Available");
            }
    }
    private void validateBookedstatus(SlotStatus status){
        if(status == SlotStatus.BOOKED)
        {
            throw new IllegalArgumentException("Cannot change if Slot is Booked");
        }
    }
    private void validateTime(LocalDateTime time)
    {
        if(time == null)
        {
            throw new IllegalArgumentException("Time cannot be null");
        }
    }

    private void validateEndTime(LocalDateTime startTime,
                                 LocalDateTime endTime)
    {
        if(endTime.isBefore(startTime) )
        {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
    }

}
