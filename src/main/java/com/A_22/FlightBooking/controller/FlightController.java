package com.A_22.FlightBooking.controller;

import com.A_22.FlightBooking.model.Flight;
import com.A_22.FlightBooking.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Get all flights and show them in an HTML table
    @GetMapping
    public String getAllFlights(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        model.addAttribute("flights", flights);  // Add the list of flights to the model
        return "flights";  // Thymeleaf template for displaying flights
    }
}
