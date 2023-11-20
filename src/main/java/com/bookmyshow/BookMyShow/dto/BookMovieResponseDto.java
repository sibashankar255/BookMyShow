package com.bookmyshow.BookMyShow.dto;

import com.bookmyshow.BookMyShow.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private ResponseStatus responseStatus;
    private int amount;
    private Long bookingId;

}
