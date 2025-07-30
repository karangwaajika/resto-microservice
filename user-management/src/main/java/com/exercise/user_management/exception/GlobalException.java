package com.exercise.user_management.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Hidden
public class GlobalException {

    @ExceptionHandler(UserExistsException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleUserExists(UserExistsException exception, ServerWebExchange exchange) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", HttpStatus.CONFLICT.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("message", exception.getMessage());
        body.put("path", exchange.getRequest().getPath().value());
        // sessionId not always present in WebFlux; if needed, implement differently
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(body));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleUserNotFound(UserNotFoundException exception, ServerWebExchange exchange) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("message", exception.getMessage());
        body.put("path", exchange.getRequest().getPath().value());
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(body));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleBadCredentialsException(BadCredentialsException exception, ServerWebExchange exchange) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", HttpStatus.UNAUTHORIZED.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("message", exception.getMessage());
        body.put("path", exchange.getRequest().getPath().value());
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleAccessDeniedException(AccessDeniedException exception, ServerWebExchange exchange) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", HttpStatus.FORBIDDEN.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("message", exception.getMessage());
        body.put("path", exchange.getRequest().getPath().value());
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(body));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleException(Exception exception, ServerWebExchange exchange) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("message", exception.getMessage());
        body.put("path", exchange.getRequest().getPath().value());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body));
    }

    @ExceptionHandler(Error.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleError(Error error, ServerWebExchange exchange) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("message", error.getMessage());
        body.put("path", exchange.getRequest().getPath().value());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body));
    }
}
