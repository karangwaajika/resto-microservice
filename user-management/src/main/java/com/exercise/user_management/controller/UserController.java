package com.exercise.user_management.controller;

import com.exercise.user_management.dto.UserRegisterDto;
import com.exercise.user_management.dto.UserResponseDto;
import com.exercise.user_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("users")
@Tag(name = "User Controller", description = "Manage all the User's urls")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register user",
            description = "This request inserts a user to the database and returns " +
                          "the inserted user ")
    public ResponseEntity<UserResponseDto> registerUser(
            @RequestBody UserRegisterDto userRegisterDto
    ) {
        UserResponseDto savedUser = this.userService.create(userRegisterDto);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping(name = "view_users", path = "/view")
    @Operation(summary = "View users",
            description = "This method applies pagination for efficient retrieval " +
                          "of users list")
    public Page<UserResponseDto> viewUsers(Pageable pageable){
        return this.userService.findAll(pageable);
    }
}
