package com.example.final_project.model.appeal;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppealResponse {
    private LocalDateTime detectedTime;
    private String evidenceImage;
    private String licensePlate;
    private Long detectionId;
    private Long appealId;

}
