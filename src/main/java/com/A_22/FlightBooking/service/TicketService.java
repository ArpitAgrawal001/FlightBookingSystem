package com.A_22.FlightBooking.service;

import com.A_22.FlightBooking.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {
    private final List<Ticket> tickets = new ArrayList<>();

    public Ticket createTicket(Ticket ticket) {
        // Generate a shortened UUID (first 8 characters of a random UUID)
        String shortUuid = UUID.randomUUID().toString().substring(0, 8);
        ticket.setId(shortUuid);  // Set the shortened UUID as the ID
        ticket.setStatus("BOOKED");
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        return tickets;
    }

    public Optional<Ticket> getTicketById(String id) {
        return tickets.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    // Update ticket status to "CANCELLED"
    public boolean cancelTicket(String id) {
        Optional<Ticket> ticket = getTicketById(id);
        if (ticket.isPresent()) {
            ticket.get().setStatus("CANCELLED");  // Update status to "CANCELLED"
            return true;
        }
        return false;  // Return false if ticket is not found
    }
}
