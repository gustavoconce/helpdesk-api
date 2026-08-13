package com.gustavo.helpdeskapi.service;

import com.gustavo.helpdeskapi.entity.Category;
import com.gustavo.helpdeskapi.entity.User;
import com.gustavo.helpdeskapi.repository.UserRepository;
import com.gustavo.helpdeskapi.repository.CategoryRepository;

import com.gustavo.helpdeskapi.entity.Ticket;
import com.gustavo.helpdeskapi.exception.ResourceNotFoundException;
import com.gustavo.helpdeskapi.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Ticket createTicket(Ticket ticket) {

        User user = userRepository.findById(ticket.getUser().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado!"));

        Category category = categoryRepository.findById(ticket.getCategory().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada!"));

        ticket.setUser(user);
        ticket.setCategory(category);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id){
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket não Encontrado!") );
    }

    public Ticket updateTicket(Long id, Ticket ticketData) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket não encontrado!"));

        User user = userRepository.findById(ticketData.getUser().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado!"));

        Category category = categoryRepository.findById(ticketData.getCategory().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada!"));

        ticket.setTitle(ticketData.getTitle());
        ticket.setDescription(ticketData.getDescription());
        ticket.setPriority(ticketData.getPriority());
        ticket.setStatus(ticketData.getStatus());
        ticket.setUser(user);
        ticket.setCategory(category);

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id){

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket Não Encontrado!"));

        ticketRepository.delete(ticket);

    }

}
