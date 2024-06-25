package com.example.final_project.model.userScore;

import java.util.List;
import lombok.Data;

@Data
public class UserScoreResponseDate {
    private String name;
    private Integer score;
    private List<DetectionDetail> detections;

    @Data
    public static class DetectionDetail {
        private Integer date;
        private String day;
        private Integer month;
        private String monthName;
        private String licensePlate;
        private String evident;
    }
}
