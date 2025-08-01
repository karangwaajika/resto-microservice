package com.exercise.user_management.service.impl;

import com.exercise.user_management.dto.UserRegisterDto;
import com.exercise.user_management.dto.UserResponseDto;
import com.exercise.user_management.exception.UserExistsException;
import com.exercise.user_management.mapper.UserMapper;
import com.exercise.user_management.messaging.RabbitMQProducer;
import com.exercise.user_management.messaging.UserEventProducer;
import com.exercise.user_management.model.UserEntity;
import com.exercise.user_management.repository.UserRepository;
import com.exercise.user_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.exercise.event.StartCookingCommand;
import org.exercise.event.UserCreatedEvent;
import org.exercise.util.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserEventProducer userEventProducer;
    private final RabbitMQProducer rabbitMQProducer;

    @Override
    public UserResponseDto create(UserRegisterDto userDto) {
        if(findByEmail(userDto.getEmail()).isPresent()){
            throw new UserExistsException(
                    String.format("A user with the email '%s' already exist",
                            userDto.getEmail()));
        }
        // hash the password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        UserEntity userEntity = this.userMapper.toEntity(userDto);
        UserEntity savedUser = this.userRepository.save(userEntity);


        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
        // populate contents
        userEventProducer.sendUserCreatedEvent(userCreatedEvent);

        // using rabbitMQ
        rabbitMQProducer.sendStartCookingCommand(
                new StartCookingCommand(savedUser.getId(), savedUser.getEmail()));

        return this.userMapper.toDto(savedUser);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public Page<UserResponseDto> findAll(Pageable pageable) {
        Page<UserEntity> users = this.userRepository.findAll(pageable);
        return users.map(this.userMapper::toDto);
    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return this.userRepository.findById(userId);
    }
}
