package com.adarsh.artistbooking.entity;

import com.adarsh.artistbooking.enums.OfferStatus;

import java.time.LocalDateTime;

import static com.adarsh.artistbooking.enums.OfferStatus.Cancelled;

public class Offer {
    private long id;
    private long eventId;
    private long slotId;
    private Money proposedAmount;
    private LocalDateTime expiryTime;
    private OfferStatus status;

    public Offer(long id, long eventId, long slotId, Money proposedAmount, LocalDateTime expiryTime) {
        this.id = id;
        this.eventId = eventId;
        this.slotId = slotId;
        validateProposedAmount(proposedAmount);
        this.proposedAmount = proposedAmount;
        validateExpiryTime(expiryTime);
        this.expiryTime = expiryTime;
        this.status = OfferStatus.InProgress;
    }

    // getters
    public OfferStatus getStatus() {
        return status;
    }
    public Money getProposedAmount() {
        return proposedAmount;
    }
    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    //accept();
    public void accept() {
        ensureInProgress();
        status = OfferStatus.Accepted;
    }
    // reject()
    public void reject() {
        ensureInProgress();
        status = OfferStatus.Rejected;
    }
    //cancel();
    public void cancel() {
        ensureInProgress();
        status = OfferStatus.Cancelled;
    }
    //expire();
    public void expire() {
        ensureInProgress();
        status = OfferStatus.Expired;
    }
    //updateProposedAmount(Money newAmount);
    public void updateProposedAmount(Money amount) {
        ensureInProgress();
        validateProposedAmount(amount);
        this.proposedAmount = amount;
    }
    public void updateExpiryTime(LocalDateTime time) {
        ensureInProgress();
        validateExpiryTime(time);
        this.expiryTime = time;
    }

    public long getId() {
        return id;
    }
    public long getEventId() {
        return eventId;
    }

    public void assignId(long id){
        this.id = id;
    }

    private void validateProposedAmount(Money amount) {
        if(amount == null)
        {
            throw new IllegalArgumentException("Cannot propose null amount");
        }
        if(amount.amount <=0)
        {
            throw new IllegalArgumentException("Cannot propose zero or negative amount");
        }
    }
    private void validateExpiryTime(LocalDateTime time) {
        if(time == null)
        {
            throw new IllegalArgumentException("Cannot propose null time");
        }
        if(time.isBefore(LocalDateTime.now()))
        {
            throw new IllegalStateException("Cannot propose before time");
        }
    }
    private void ensureInProgress() {
        if (status != OfferStatus.InProgress) {
            throw new IllegalStateException(
                    "Offer is already " + status + ". State transition is not allowed."
            );
        }
    }
}
