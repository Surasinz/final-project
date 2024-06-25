package com.example.final_project.model.user;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class UserRegisterResponse {
    private String name;
    private String surName;
    private String faculty;
    private String major;
    private String studentId;
    private String gender;
    private String password;
    private String licensePlate;
}
