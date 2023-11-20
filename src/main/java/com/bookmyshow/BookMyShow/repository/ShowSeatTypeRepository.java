package com.bookmyshow.BookMyShow.repository;

import com.bookmyshow.BookMyShow.models.Show;
import com.bookmyshow.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShow(Show show);
    //select * from show_seat_type where show_id = show_id;

}
