package com.bookmyshow.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Theatre theatre;

    private Date startTime;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}

/**
 * 1 ---------1
 * Show ---- Movie -> M:1
 *  M          1
 *
 * 1            1
 * Show ----- Screen -> M:1
 * M           1
 *
 * Show -> movie, theatre, screen, time
 *
 * 1           1
 * Show ---- Theater -> M:1
 * M          1
 */
