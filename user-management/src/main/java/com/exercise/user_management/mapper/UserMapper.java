package com.exercise.user_management.mapper;

import com.exercise.user_management.dto.UserRegisterDto;
import com.exercise.user_management.dto.UserResponseDto;
import com.exercise.user_management.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(UserEntity user);
    UserEntity toEntity(UserRegisterDto userRegisterDto);
}
