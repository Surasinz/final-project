package com.example.final_project.model.detection;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Data
@Component
public class DetectionResponse {
    private Integer date;
    private String day;
    private Integer month;
    private String monthName;
    private Time time;
    private String name;
    private String evidence;
    private String faculty;
    private String studentId;
    private String LicensePlate;
    private String responseCode;
}
