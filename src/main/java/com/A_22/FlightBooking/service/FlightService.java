package com.A_22.FlightBooking.service;

import com.A_22.FlightBooking.model.Flight;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private List<Flight> flights = new ArrayList<>();

    @PostConstruct
    public void loadFlights() {
        flights.add(new Flight("1", "Indigo", "Delhi", "Mumbai", List.of("2025-03-21", "2025-03-22")));
        flights.add(new Flight("2", "Air India", "Bangalore", "Chennai", List.of("2025-03-21", "2025-03-23")));
        flights.add(new Flight("3", "SpiceJet", "Kolkata", "Hyderabad", List.of("2025-03-24", "2025-03-25")));
        flights.add(new Flight("4", "GoAir", "Delhi", "Chennai", List.of("2025-03-26", "2025-03-27")));
        flights.add(new Flight("5", "Air India", "Mumbai", "Bangalore", List.of("2025-03-28", "2025-03-29")));
        flights.add(new Flight("6", "Vistara", "Delhi", "Kolkata", List.of("2025-03-30", "2025-03-31")));
        flights.add(new Flight("7", "Indigo", "Chennai", "Delhi", List.of("2025-04-01", "2025-04-02")));
        flights.add(new Flight("8", "AirAsia", "Bangalore", "Hyderabad", List.of("2025-04-03", "2025-04-04")));
        flights.add(new Flight("9", "SpiceJet", "Mumbai", "Chennai", List.of("2025-04-05", "2025-04-06")));
        flights.add(new Flight("10", "Indigo", "Hyderabad", "Bangalore", List.of("2025-04-07", "2025-04-08")));
        flights.add(new Flight("11", "Air India", "Chennai", "Mumbai", List.of("2025-04-09", "2025-04-10")));
        flights.add(new Flight("12", "GoAir", "Delhi", "Hyderabad", List.of("2025-04-11", "2025-04-12")));
        flights.add(new Flight("13", "Vistara", "Bangalore", "Mumbai", List.of("2025-04-13", "2025-04-14")));
        flights.add(new Flight("14", "AirAsia", "Hyderabad", "Chennai", List.of("2025-04-15", "2025-04-16")));
        flights.add(new Flight("15", "SpiceJet", "Kolkata", "Delhi", List.of("2025-04-17", "2025-04-18")));
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public Optional<Flight> getFlightById(String id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findFirst();
    }
}
