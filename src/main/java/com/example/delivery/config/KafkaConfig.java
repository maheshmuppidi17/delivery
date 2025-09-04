package com.example.delivery.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaUtils;

@Configuration
public class KafkaConfig {

    @Bean
    public CommonErrorHandler commonErrorHandler() {
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
            (record, exception) -> {
                System.err.println("Failed to process message after retries: " + record.topic() + 
                                 "-" + record.partition() + "-" + record.offset());
                System.err.println("Error: " + exception.getMessage());
            },
            new FixedBackOff(1000L, 2L) // 1 second delay, 2 attempts
        );
        
        // Don't retry on deserialization errors
        errorHandler.addNotRetryableExceptions(DeserializationException.class);
        
        return errorHandler;
    }
}