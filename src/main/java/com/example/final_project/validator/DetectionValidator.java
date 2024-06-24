package com.example.final_project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DetectionValidator {
    public void isTimeValid(LocalDateTime dateTime) throws Exception {
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        boolean isSameDay = dateTime.toLocalDate().isEqual(today);
        boolean isPastFiveMinutes = dateTime.toLocalTime().isBefore(currentTime.minusMinutes(5));
        if (!(isSameDay&&isPastFiveMinutes)) {
            throw new Exception("Detection time is not valid. It must be today and more than 5 minutes ago.");
        }
    }
}
