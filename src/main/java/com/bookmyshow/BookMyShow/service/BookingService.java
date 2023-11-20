package com.bookmyshow.BookMyShow.service;

import com.bookmyshow.BookMyShow.exception.InvalidUserException;
import com.bookmyshow.BookMyShow.exception.ShowSeatNotAvailableException;
import com.bookmyshow.BookMyShow.models.*;
import com.bookmyshow.BookMyShow.repository.ShowRepository;
import com.bookmyshow.BookMyShow.repository.ShowSeatRepository;
import com.bookmyshow.BookMyShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculator priceCalculator;

    @Autowired
    BookingService(UserRepository userRepository, ShowRepository showRepository,
                   ShowSeatRepository showSeatRepository, PriceCalculator priceCalculator){
        this.userRepository= userRepository;
        this.showRepository=showRepository;
        this.showSeatRepository=showSeatRepository;
        this.priceCalculator = priceCalculator;
    }

    @Transactional(isolation =  Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws Exception {

        /**
         * steps:-
         * ----------start the transaction -----------------------------
         * 1.get the user from user id
         * 2.get the show from showId
         * 3.get the showSeats from list of showSeatId
         * 4.check if all the show seats are available
         * ------------------take DB lock (we will not do this) ------------
         * 5.if yes, mark the show seat status as BLOCKED
         * 6.save the updated status to DB
         * -----------release the lock--------------------------
         * 7.create the booking object (go to the payments page)
         * 8.return the booking object
         *
         * -------------end the transaction----------------------------------
         */

        //1.get the user from userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw  new Exception();
        }

        User bookedBy = optionalUser.get();

        //2.get the show from showId
        Optional<Show> optionalShow = showRepository.findById(showId);
        if (optionalShow.isEmpty()){
            throw new InvalidUserException("Invalid show");
        }

        Show show = optionalShow.get();

        //3.get the showSeats from list of showSeatId
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        //4.check if all the show seats are available
        for (ShowSeat showSeat : showSeats){
            if (showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatNotAvailableException("ShowSeat not available");
            }
        }

        List<ShowSeat> savedShowSeat = new ArrayList<>();

        //5.if yes, mark the show seat status as BLOCKED
        //6.save the updated status to DB
        for (ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeat.add(showSeatRepository.save(showSeat));
        }

        //7.create the booking object
        Booking booking = new Booking();
        booking.setUser(bookedBy);
        booking.setShow(show);
        booking.setCreatedAt(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setPayments(new ArrayList<>());
        booking.setAmount(priceCalculator.calculatePrice(savedShowSeat,show));

        return booking;

    }
}
