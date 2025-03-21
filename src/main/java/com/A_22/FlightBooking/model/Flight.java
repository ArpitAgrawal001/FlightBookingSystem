package com.A_22.FlightBooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String id;
    private String airline;
    private String source;
    private String destination;
    private List<String> scheduleDates;
}
