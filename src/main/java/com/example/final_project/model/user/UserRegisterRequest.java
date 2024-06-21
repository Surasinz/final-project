package com.example.final_project.model.user;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name;
    private String surName;
    private String studentID;
    private String password;
    private String gender;
    private String faculty;
    private String field;
    private String email;
    private String profile;
    private String licensePlate;
}
