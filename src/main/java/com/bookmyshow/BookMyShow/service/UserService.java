package com.bookmyshow.BookMyShow.service;

import com.bookmyshow.BookMyShow.models.User;
import com.bookmyshow.BookMyShow.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    public User login(String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw  new Exception("user does not exist !!!");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = optionalUser.get();
        if (bCryptPasswordEncoder.matches(password,user.getPassword())){
            return user;
        }

        throw new RuntimeException("Incorrect password");
    }

    public User signUp(String email, String password) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isEmpty()){
            return login(email,password);
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        //save in the database
        User savedUser = userRepository.save(user);

        return savedUser;
    }
}

/**
 * Password Encrypt:-
 *
 * BCrypt encoder
 *
 *
 */
