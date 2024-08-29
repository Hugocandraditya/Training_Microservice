package com.example.jwt_project.service;

import com.example.jwt_project.entity.UserDto;
import com.example.jwt_project.model.User;
import com.example.jwt_project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public UserService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserDto input) {
        var userModel = userRepository.findByUsername(input.getUsername());
        if(userModel.isPresent())
            throw new RuntimeException("not unik");
        User user = new User();
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(UserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        List<User> users = new ArrayList<>(userRepository.findAll());
        log.info(users.toString());

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>(userRepository.findAll());
        log.info(users.toString());
        return users;
    }
}