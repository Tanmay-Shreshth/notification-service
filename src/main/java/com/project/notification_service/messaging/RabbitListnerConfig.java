package com.project.notification_service.messaging;


import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitListnerConfig {

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jsonMessageConverter) {
        
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter);
        factory.setDefaultRequeueRejected(false);
        
        factory.setAdviceChain(
            RetryInterceptorBuilder.stateless()
            .maxAttempts(3)
            .recoverer(new RejectAndDontRequeueRecoverer())
            .build()
        );

        return factory;
        
    }
    
}
