package com.exercise.user_management.messaging;

import lombok.RequiredArgsConstructor;
import org.exercise.event.StartCookingCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendStartCookingCommand(StartCookingCommand command) {
        rabbitTemplate.convertAndSend("order.cooking", command);
    }
}
