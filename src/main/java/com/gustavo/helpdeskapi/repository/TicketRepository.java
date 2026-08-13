package com.gustavo.helpdeskapi.repository;

import com.gustavo.helpdeskapi.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
