package com.adarsh.artistbooking.entity;

import com.adarsh.artistbooking.enums.BookingStatus;
import com.adarsh.artistbooking.enums.PaymentStatus;

public class Booking {
    private long id;

    private long offerId;

    private BookingStatus status;

    private PaymentStatus paymentStatus;

    private Money advanceAmount;

    public Booking(long offerId,
                   Money advanceAmount){
        this.id = 0;
        this.offerId = offerId;
        validateadvanceAmount(advanceAmount);
        this.advanceAmount = advanceAmount;
        this.paymentStatus = PaymentStatus.PENDING;
        this.status = BookingStatus.CREATED;
    }
    public long getId() {
        return id;
    }
    public long getOfferId() {
        return offerId;
    }
    public BookingStatus getStatus() {
        return status;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public Money getAdvanceAmount() {
        return advanceAmount;
    }

    public void assignId(long id)
    {
        this.id = id;
    }
    public void confirmPayment(){
        // only creted booking
        ensureCreatedBooking();
        this.status = BookingStatus.CONFIRMED;
        this.paymentStatus = PaymentStatus.PAID;

    }
    public void cancel(){
        // only creted or confirmed
        ensureCreatedorConfirmed();
        this.status = BookingStatus.CANCELLED;
    }
    public void complete(){
        // only confirmed
        ensureCreatedBooking();
        this.status = BookingStatus.COMPLETED;
    }
    private void ensureCreatedBooking(){
        if(status != BookingStatus.CREATED){
            throw new IllegalStateException("Booking status is not CREATED ");
        }
    }
    private void ensureCreatedorConfirmed(){
        if(status != BookingStatus.CONFIRMED && status != BookingStatus.COMPLETED){
            throw new IllegalStateException("Booking status is not CONFIRMED or COMPLETED");

        }
    }

    private void ensureConfirmedBooking(){
        if(status != BookingStatus.CONFIRMED){
            throw new IllegalStateException("Booking status is not CONFIRMED ");
        }
    }
    private void validateadvanceAmount(Money advanceAmount){
        if(advanceAmount == null){
            throw new IllegalArgumentException("advance amount is null");
        }
        if(advanceAmount.amount <=0){
            throw new IllegalArgumentException("advance amount must be greater than 0");
        }
    }

}
