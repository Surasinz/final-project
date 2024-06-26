package com.example.final_project.business.detection;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DetectionBusiness {
    public String reformatRecognition(String recognition) {
        String pattern = "(\\d+[ก-๙]+\\d+)([A-Za-z ]+)\\s*\\(([^)]+)\\)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(recognition);

        if (m.find()) {
            recognition = m.group(1) + m.group(3);
        }

        return recognition;
    }
}
