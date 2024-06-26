package com.example.final_project.model.appeal;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class AppealResponse {
    private String detectedTime;
    private String evidenceImage;
    private String licensePlate;
    private Long detectionId;
    private Long appealId;

    public void formatDetectedTime(LocalDateTime detectedTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
        this.detectedTime = detectedTime.format(formatter);
    }
}
