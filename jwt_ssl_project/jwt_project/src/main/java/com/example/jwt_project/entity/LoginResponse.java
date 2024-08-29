package com.example.jwt_project.entity;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private Long expiresIn;
}
