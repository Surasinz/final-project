package com.example.final_project.model.detection;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DetectionRequest {
    private String licensePlateFound;
    private String evidenceImg;
    private Long userDetection;
}
