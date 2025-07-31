package com.exercise.order_service.messaging;

import org.exercise.event.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedEventListener {

    @KafkaListener(topics = "user.create", groupId = "order-service-group")
    public void listen(UserCreatedEvent event) {
        System.out.println("Received user.created event: " + event);
        // TODO: save user in local read DB or cache, if needed
    }
}

