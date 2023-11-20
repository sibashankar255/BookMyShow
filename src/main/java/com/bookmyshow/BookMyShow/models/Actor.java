package com.bookmyshow.BookMyShow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.PriorityQueue;

@Getter
@Setter
@Entity
public class Actor extends BaseModel{
    private String name;

}
