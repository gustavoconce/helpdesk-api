package com.gustavo.helpdeskapi.mapper;

import com.gustavo.helpdeskapi.dto.TicketDTO;
import com.gustavo.helpdeskapi.entity.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket){
        return new TicketDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getPriority(),
                ticket.getUser().getId(),
                ticket.getCategory().getId()

        );
    }

}
