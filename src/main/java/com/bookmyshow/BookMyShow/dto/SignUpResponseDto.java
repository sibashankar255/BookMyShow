package com.bookmyshow.BookMyShow.dto;

import com.bookmyshow.BookMyShow.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private ResponseStatus responseStatus;
    private Long userId;
}
