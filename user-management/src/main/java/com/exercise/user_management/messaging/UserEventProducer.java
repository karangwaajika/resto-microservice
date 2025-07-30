package com.exercise.user_management.messaging;

import com.exercise.user_management.event.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducer {
    // topi-name and contents (userCreatedEvent)
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        kafkaTemplate.send("user.created", event);
    }
}

