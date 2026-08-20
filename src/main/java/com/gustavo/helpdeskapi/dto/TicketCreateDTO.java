package com.gustavo.helpdeskapi.dto;

import com.gustavo.helpdeskapi.entity.TicketPriority;
import com.gustavo.helpdeskapi.entity.TicketStatus;

public class TicketCreateDTO {

    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private Long userId;
    private Long categoryId;

    public TicketCreateDTO() {
    }

    public TicketCreateDTO(
            String title,
            String description,
            TicketStatus status,
            TicketPriority priority,
            Long userId,
            Long categoryId
    ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}