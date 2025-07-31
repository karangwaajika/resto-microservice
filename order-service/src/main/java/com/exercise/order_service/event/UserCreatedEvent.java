package com.exercise.order_service.event;

public record UserCreatedEvent(Long id, String email, String role) {
}
