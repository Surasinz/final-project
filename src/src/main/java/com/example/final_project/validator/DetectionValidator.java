package com.example.final_project.validator;

import com.example.final_project.converter.detection.DetectionConverter;
import com.example.final_project.model.detection.DetectionEntity;
import com.example.final_project.repository.detection.DetectionRepository;
import com.example.final_project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;

@Component
public class DetectionValidator {
    @Autowired
    DetectionRepository detectionRepository;
    public void validateDetectionTime(LocalDateTime detectionTime,LocalDateTime detected) throws Exception {
        boolean isSameDay = detectionTime.toLocalDate().isEqual(ChronoLocalDate.from(detected));
        boolean isPastFiveMinutes = detectionTime.isBefore(detected.minusMinutes(5));

        if (!(isSameDay && isPastFiveMinutes)) {
            throw new Exception("Detection time is not valid. It must be today and more than 5 minutes ago.");
        }
    }

    public void validateAndSaveIfNeeded(DetectionEntity detectionEntity,LocalDateTime detected) throws Exception {
        if (detected != null) {
            validateDetectionTime(detected, detectionEntity.getDetectionTime());
        } else {
           // detectionRepository.save(detectionEntity);
        }
    }

}
