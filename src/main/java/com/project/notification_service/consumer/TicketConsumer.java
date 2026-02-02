package com.project.notification_service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.project.notification_service.dto.TicketEvent;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class TicketConsumer {
    
    @RabbitListener(queues = "ticket.notification.queue", containerFactory = "rabbitListenerContainerFactory")
    public void handleTicketEvent(TicketEvent event){


    //     if (event.getTicketId() != null) {
    //     throw new RuntimeException("Simulated failure");
    // }

    // log.info("Processed event successfully");


        log.info("Received ticket event: {}", event);

        switch (event.getEventType()) {
            case CREATED:
                log.info("sending notification for ticket creation: {}", event.getTicketId());
                break;

            case ASSIGNED:
                log.info("sending notification for ticket assignment: {}", event.getTicketId());
                break;
            
            case STATUS_CHANGED:
                log.info("sending notification for ticket status change: {}", event.getTicketId());
                break;
        
            default:
                break;
        }
    }
}
