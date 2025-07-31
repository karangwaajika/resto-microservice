package com.exercise.order_service.messaging;

import org.exercise.event.StartCookingCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StartCookingListener {

    @RabbitListener(queues = "order.cooking")
    public void handleStartCooking(StartCookingCommand command) {
        System.out.println("🔥 Start cooking for user: " + command.email());
    }
}
