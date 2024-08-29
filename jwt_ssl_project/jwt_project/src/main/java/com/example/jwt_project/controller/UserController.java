package com.example.jwt_project.controller;

import com.example.jwt_project.entity.LoginResponse;
import com.example.jwt_project.entity.UserDto;
import com.example.jwt_project.model.User;
import com.example.jwt_project.service.UserService;
import com.example.jwt_project.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDto userDto) {
        User registeredUser = userService.register(userDto);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody UserDto userDto) {
        User authenticatedUser = userService.authenticate(userDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
