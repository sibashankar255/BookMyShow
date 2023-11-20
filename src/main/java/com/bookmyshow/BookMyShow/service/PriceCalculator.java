package com.bookmyshow.BookMyShow.service;

import com.bookmyshow.BookMyShow.models.Show;
import com.bookmyshow.BookMyShow.models.ShowSeat;
import com.bookmyshow.BookMyShow.models.ShowSeatType;
import com.bookmyshow.BookMyShow.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {

    private ShowSeatRepository showSeatRepository;

    @Autowired
    PriceCalculator(ShowSeatRepository showSeatRepository){
        this.showSeatRepository=showSeatRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Show show){
        List<ShowSeatType> showSeatTypes = showSeatRepository.findAllByShow(show);

        int amount=0;
        for (ShowSeat showSeat : showSeats){
            for (ShowSeatType showSeatType : showSeatTypes){
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount+= showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
