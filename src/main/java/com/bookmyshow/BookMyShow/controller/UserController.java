package com.bookmyshow.BookMyShow.controller;

import com.bookmyshow.BookMyShow.dto.SignUpRequestDto;
import com.bookmyshow.BookMyShow.dto.SignUpResponseDto;
import com.bookmyshow.BookMyShow.models.ResponseStatus;
import com.bookmyshow.BookMyShow.models.User;
import com.bookmyshow.BookMyShow.repository.UserRepository;
import com.bookmyshow.BookMyShow.service.UserService;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService=userService;

    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) throws Exception {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(signUpRequestDto.getEmail(),
                    signUpRequestDto.getPassword());
            signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            signUpResponseDto.setUserId(user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return signUpResponseDto;
    }
}
