package com.A_22.FlightBooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String id;  // Shortened UUID as String
    private String flightId;
    private String passengerName;
    private String email;
    private String status;  // BOOKED, CANCELLED
}
