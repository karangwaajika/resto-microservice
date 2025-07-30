package com.exercise.user_management.service;

import com.exercise.user_management.dto.UserRegisterDto;
import com.exercise.user_management.dto.UserResponseDto;
import com.exercise.user_management.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserResponseDto create(UserRegisterDto userDto);
    Optional<UserEntity> findByEmail(String email);
    Page<UserResponseDto> findAll(Pageable pageable);
    Optional<UserEntity> findById(Long userId);

}
