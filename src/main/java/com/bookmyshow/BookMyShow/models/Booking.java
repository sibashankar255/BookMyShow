package com.bookmyshow.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @OneToMany
    private List<ShowSeat> showSeats;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    private double amount;

    @OneToMany
    private List<Payment> payments;

}

/**
 * 1           1
 * ticket ----User  -> M:1
 * M             1
 *
 * 1           1
 * ticket --- Show
 *  M           1
 *
 *  1             M
 *  ticket ----- ShowSeat -> M:M
 *  M             1
 *  one ticket can have many show seats
 *  one show seat can be booked in one booking if one is cancelled and another is booked
 *
 *
 * 1          M
 * ticket --- payment -> 1:M
 *  1          1
 */


