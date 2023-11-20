package com.bookmyshow.BookMyShow.repository;

import com.bookmyshow.BookMyShow.models.Show;
import com.bookmyshow.BookMyShow.models.ShowSeat;
import com.bookmyshow.BookMyShow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findAllById(List<Long> showSeatIds);

    ShowSeat save(ShowSeat showSeat);

    List<ShowSeatType> findAllByShow(Show show);
    /*
    Insert -> if showSeat object is not present in the DB.
           -> If input showSeat object does not contain the id.

    Update -> updating the existing object in the DB.

    //save method returns an updated object

     */

}
