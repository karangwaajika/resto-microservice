package com.exercise.user_management.event;

public record UserCreatedEvent(Long id, String email, String role) {
}
