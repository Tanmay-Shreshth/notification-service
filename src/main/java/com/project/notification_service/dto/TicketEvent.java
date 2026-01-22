package com.project.notification_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class TicketEvent {
    
    private UUID ticketId;
    private TicketEventType eventType;
    private String actor;
    private String payload;
    private LocalDateTime timestamp;

    
    
}
