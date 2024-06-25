package com.example.final_project.model.user;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String name;
    private String studentID;
    private String role;
    private Long userId;
}
