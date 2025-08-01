package com.exercise.user_management.dto;

import org.exercise.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDto {
    private String email;
    private String password;
    private Role role;
}
