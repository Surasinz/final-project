package com.example.final_project.model.userScore;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserScoreRequest {
    private Long userId;
    private Integer score;
}
