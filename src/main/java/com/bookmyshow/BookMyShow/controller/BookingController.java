package com.bookmyshow.BookMyShow.controller;

import com.bookmyshow.BookMyShow.dto.BookMovieRequestDto;
import com.bookmyshow.BookMyShow.dto.BookMovieResponseDto;
import com.bookmyshow.BookMyShow.models.Booking;
import com.bookmyshow.BookMyShow.models.ResponseStatus;
import com.bookmyshow.BookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class BookingController {

    private BookingService bookingService;

    @Autowired
    BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto){
        BookMovieResponseDto responseDto = new BookMovieResponseDto();

        try {
            Booking booking = bookingService.bookMovie(requestDto.getUserId(),
                    requestDto.getShowId(), requestDto.getShowSeatId());
            responseDto.setBookingId(booking.getId());
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            throw  new RuntimeException();
        }

        return responseDto;


    }
}
