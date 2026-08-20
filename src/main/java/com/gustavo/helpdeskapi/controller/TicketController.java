package com.gustavo.helpdeskapi.controller;

import com.gustavo.helpdeskapi.dto.TicketCreateDTO;
import com.gustavo.helpdeskapi.dto.TicketDTO;
import com.gustavo.helpdeskapi.entity.Ticket;
import com.gustavo.helpdeskapi.service.TicketService;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO createTicket(@Valid @RequestBody TicketCreateDTO dto) {
        return ticketService.createTicket(dto);
    }

    @GetMapping
    public List<TicketDTO> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketDTO getTicketById(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @Valid @RequestBody Ticket ticketData){
        return ticketService.updateTicket(id, ticketData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }

}
