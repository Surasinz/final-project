package com.example.final_project.model.userScore;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class UserScoreResponseDate {
    private String name;
    private Integer score;
    private List<DetectionDetail> detections;

    @Data
    public static class DetectionDetail {
        private Long userId;
        private Integer date;
        private String day;
        private String time;
        private Integer month;
        private String monthName;
        private String licensePlate;
        private String evident;
        private Long id;
    }
}
