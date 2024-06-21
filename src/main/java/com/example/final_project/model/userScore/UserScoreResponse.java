package com.example.final_project.model.userScore;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UserScoreResponse {
    private String name;
    private Integer score;
    private Date date;
    private String licensePlate;
}
