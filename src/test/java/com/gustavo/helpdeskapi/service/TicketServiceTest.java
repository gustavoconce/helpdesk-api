package com.gustavo.helpdeskapi.service;

import com.gustavo.helpdeskapi.dto.TicketCreateDTO;
import com.gustavo.helpdeskapi.dto.TicketDTO;
import com.gustavo.helpdeskapi.entity.Category;
import com.gustavo.helpdeskapi.entity.Ticket;
import com.gustavo.helpdeskapi.entity.TicketPriority;
import com.gustavo.helpdeskapi.entity.TicketStatus;
import com.gustavo.helpdeskapi.entity.User;
import com.gustavo.helpdeskapi.exception.ResourceNotFoundException;
import com.gustavo.helpdeskapi.repository.CategoryRepository;
import com.gustavo.helpdeskapi.repository.TicketRepository;
import com.gustavo.helpdeskapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TicketService ticketService;


    @Test
    void shouldCreateTicket() {

        TicketCreateDTO dto = new TicketCreateDTO(
                "Computador não liga",
                "O computador da recepção não inicia.",
                TicketStatus.OPEN,
                TicketPriority.HIGH,
                1L,
                1L
        );

        User user = new User();
        user.setId(1L);
        user.setName("Gustavo");
        user.setEmail("gustavo@email.com");
        user.setPassword("123456");
        user.setRole("USER");

        Category category = new Category();
        category.setId(1L);
        category.setName("Hardware");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        Ticket savedTicket = new Ticket();

        savedTicket.setId(1L);
        savedTicket.setTitle("Computador não liga");
        savedTicket.setDescription("O computador da recepção não inicia.");
        savedTicket.setStatus(TicketStatus.OPEN);
        savedTicket.setPriority(TicketPriority.HIGH);
        savedTicket.setUser(user);
        savedTicket.setCategory(category);

        when(ticketRepository.save(any(Ticket.class)))
                .thenReturn(savedTicket);

        TicketDTO result = ticketService.createTicket(dto);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Computador não liga", result.getTitle());
        Assertions.assertEquals(TicketStatus.OPEN, result.getStatus());
        Assertions.assertEquals(TicketPriority.HIGH, result.getPriority());
        Assertions.assertEquals(1L, result.getUserId());
        Assertions.assertEquals(1L, result.getCategoryId());

        verify(ticketRepository).save(any(Ticket.class));
    }


    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {

        TicketCreateDTO dto = new TicketCreateDTO(
                "Computador não liga",
                "O computador não inicia.",
                TicketStatus.OPEN,
                TicketPriority.HIGH,
                999L,
                1L
        );

        when(userRepository.findById(999L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.createTicket(dto)
        );
    }


    @Test
    void shouldThrowExceptionWhenCategoryDoesNotExist() {

        TicketCreateDTO dto = new TicketCreateDTO(
                "Computador não liga",
                "O computador não inicia.",
                TicketStatus.OPEN,
                TicketPriority.HIGH,
                1L,
                999L
        );

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(categoryRepository.findById(999L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.createTicket(dto)
        );
    }


    @Test
    void shouldFindTicketById() {

        Ticket ticket = new Ticket();

        ticket.setId(1L);
        ticket.setTitle("Computador não liga");
        ticket.setDescription("O computador não inicia.");
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setPriority(TicketPriority.HIGH);

        User user = new User();
        user.setId(1L);

        Category category = new Category();
        category.setId(1L);

        ticket.setUser(user);
        ticket.setCategory(category);

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        TicketDTO result = ticketService.getTicketById(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Computador não liga", result.getTitle());
        Assertions.assertEquals(1L, result.getUserId());
        Assertions.assertEquals(1L, result.getCategoryId());
    }


    @Test
    void shouldThrowExceptionWhenTicketDoesNotExist() {

        when(ticketRepository.findById(999L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.getTicketById(999L)
        );
    }


    @Test
    void shouldUpdateTicket() {

        Ticket ticket = new Ticket();

        ticket.setId(1L);
        ticket.setTitle("Título antigo");
        ticket.setDescription("Descrição antiga");

        User oldUser = new User();
        oldUser.setId(1L);

        Category oldCategory = new Category();
        oldCategory.setId(1L);

        ticket.setUser(oldUser);
        ticket.setCategory(oldCategory);

        User user = new User();
        user.setId(2L);

        Category category = new Category();
        category.setId(2L);

        Ticket ticketData = new Ticket();

        ticketData.setTitle("Computador não liga");
        ticketData.setDescription("Computador da recepção não inicia.");
        ticketData.setStatus(TicketStatus.OPEN);
        ticketData.setPriority(TicketPriority.HIGH);
        ticketData.setUser(user);
        ticketData.setCategory(category);

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(user));

        when(categoryRepository.findById(2L))
                .thenReturn(Optional.of(category));

        when(ticketRepository.save(any(Ticket.class)))
                .thenReturn(ticket);

        Ticket result = ticketService.updateTicket(1L, ticketData);

        Assertions.assertEquals(
                "Computador não liga",
                result.getTitle()
        );

        Assertions.assertEquals(
                "Computador da recepção não inicia.",
                result.getDescription()
        );

        Assertions.assertEquals(
                TicketStatus.OPEN,
                result.getStatus()
        );

        Assertions.assertEquals(
                TicketPriority.HIGH,
                result.getPriority()
        );

        Assertions.assertEquals(
                2L,
                result.getUser().getId()
        );

        Assertions.assertEquals(
                2L,
                result.getCategory().getId()
        );

        verify(ticketRepository).save(ticket);
    }


    @Test
    void shouldDeleteTicket() {

        Ticket ticket = new Ticket();
        ticket.setId(1L);

        when(ticketRepository.findById(1L))
                .thenReturn(Optional.of(ticket));

        ticketService.deleteTicket(1L);

        verify(ticketRepository).delete(ticket);
    }
}