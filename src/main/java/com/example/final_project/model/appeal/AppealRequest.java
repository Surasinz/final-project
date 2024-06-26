package com.example.final_project.model.appeal;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppealRequest {
    private Long detectionId;
    private Long userId;
}
