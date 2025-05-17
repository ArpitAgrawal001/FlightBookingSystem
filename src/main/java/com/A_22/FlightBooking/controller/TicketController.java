package com.A_22.FlightBooking.controller;

import com.A_22.FlightBooking.model.Ticket;
import com.A_22.FlightBooking.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Create a new ticket
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);
        return ResponseEntity.status(201).body(createdTicket);
    }

    // Get all tickets
    @GetMapping
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "tickets";  // Thymeleaf template for listing all tickets
    }

    // Get ticket by ID and display in table format
    @GetMapping("/{id}")
    public String getTicketById(@PathVariable String id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());  // Add the ticket to the model
            return "ticket";  // Return Thymeleaf template for displaying a single ticket
        } else {
            return "redirect:/tickets";  // Redirect if ticket is not found
        }
    }

    // Cancel ticket by ID (Update status to "CANCELLED")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelTicket(@PathVariable String id) {
        boolean isCancelled = ticketService.cancelTicket(id);
        if (isCancelled) {
            return ResponseEntity.ok("Ticket with ID " + id + " has been cancelled successfully.");
        } else {
            return ResponseEntity.status(404).body("Ticket with ID " + id + " not found.");
        }
    }

    @PutMapping("/{id}")
        public ResponseEntity<?> updateTicket(@PathVariable String id, @RequestBody Ticket ticket) {
            Optional<Ticket> existingTicket = ticketService.getTicketById(id);
            if (existingTicket.isPresent()) {
                Ticket t = existingTicket.get();
                // Update fields from incoming ticket
                t.setFlightId(ticket.getFlightId());
                t.setPassengerName(ticket.getPassengerName());
                t.setEmail(ticket.getEmail());
                t.setStatus(ticket.getStatus());
                Ticket updatedTicket = ticketService.updateTicket(t);  // You'll implement this service method
                return ResponseEntity.ok(updatedTicket);
            } else {
                return ResponseEntity.status(404).body("Ticket not found");
            }
        }

}
