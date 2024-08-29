package com.example.jwt_project.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private String username;

    @NotBlank
    private String password;
}
