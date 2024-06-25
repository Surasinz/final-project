package com.example.final_project.model.user;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserLoginRequest {
        private String studentID;
        private String password;
}
