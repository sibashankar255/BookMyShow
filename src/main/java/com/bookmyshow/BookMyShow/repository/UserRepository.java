package com.bookmyshow.BookMyShow.repository;

import com.bookmyshow.BookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //SQL queries.

    @Override
    Optional<User> findById(Long aLong);
    //select * from user where id = aLong;

    Optional<User> findByEmail(String email);
    //select * from user email_id = email;


    User save(User user);
}

/**
 * JPA (java persistence API) repository
 *
 * create a repository
 * 1.class -> interface
 * 2.extend -> JPARepository
 */
