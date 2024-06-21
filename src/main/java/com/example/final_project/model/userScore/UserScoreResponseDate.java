package com.example.final_project.model.userScore;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserScoreResponseDate {
    private Integer date;
    private String day;
    private Integer month;
    private String monthName;
    private String licensePlate;
    private String evident;
    private Integer score;
}
