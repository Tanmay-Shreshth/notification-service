package com.project.notification_service.messaging;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.ticket-events-dlq}")
    private String deadLetterQueue;

    @Value("${app.rabbitmq.ticket-events-queue}")   
    private String mainQueue;

    @Bean
    public Queue ticketEventsQueue() {
        return QueueBuilder.durable(mainQueue)
        .deadLetterExchange("")
        .deadLetterRoutingKey(deadLetterQueue)
                .build();
    }

    @Bean
    public Queue ticketEventsDeadLetterQueue() {
        return QueueBuilder.durable(deadLetterQueue).build();
    }

    
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
