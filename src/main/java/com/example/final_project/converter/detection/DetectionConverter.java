package com.example.final_project.converter.detection;

import com.example.final_project.model.detection.DetectionEntity;
import com.example.final_project.model.detection.DetectionRequest;
import com.example.final_project.model.detection.DetectionResponse;
import com.example.final_project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetectionConverter {
    @Autowired
    UserRepository userRepository;
    public List<DetectionResponse> entitiesToResponses(List<DetectionEntity> detectionEntities, String name) {
        return detectionEntities.stream()
                .map(detectionEntity -> entityToResponse(detectionEntity, name))
                .collect(Collectors.toList());
    }

    public DetectionResponse entityToResponse(DetectionEntity detectionEntity, String name) {
        DetectionResponse response = new DetectionResponse();
        response.setName(name);
        response.setEvidence(detectionEntity.getEvidenceImg());
        response.setDate(detectionEntity.getDetectionTime().getDayOfMonth());
        response.setMonth(detectionEntity.getDetectionTime().getMonthValue());
        response.setDay(String.valueOf(detectionEntity.getDetectionTime().getDayOfWeek()));
        response.setMonthName(String.valueOf(detectionEntity.getDetectionTime().getMonth()));
        response.setTime(Time.valueOf(detectionEntity.getDetectionTime().toLocalTime()));
        response.setLicensePlate(userRepository.findLicensePlateByName(name));
        response.setResponseCode("200");
        return response;
    }

    public List<DetectionResponse> allEntitiesToResponses(List<DetectionEntity> detectionEntities, List<String> names, List<String> faculties) {
        List<DetectionResponse> responseList = new ArrayList<>();
        for (int i = 0; i < detectionEntities.size(); i++) {
            DetectionEntity detectionEntity = detectionEntities.get(i);
            String name = names.get(i);
            String faculty = faculties.get(i);
            DetectionResponse response = entityToFacultyResponse(detectionEntity, name, faculty);
            responseList.add(response);
        }
        return responseList;
    }

    public DetectionResponse entityToFacultyResponse(DetectionEntity detectionEntity, String name, String faculty) {
        DetectionResponse response = new DetectionResponse();
        response.setName(name);
        response.setFaculty(faculty);
        response.setEvidence(detectionEntity.getEvidenceImg());
        response.setDate(detectionEntity.getDetectionTime().getDayOfMonth());
        response.setMonth(detectionEntity.getDetectionTime().getMonthValue());
        response.setDay(String.valueOf(detectionEntity.getDetectionTime().getDayOfWeek()));
        response.setMonthName(String.valueOf(detectionEntity.getDetectionTime().getMonth()));
        response.setTime(Time.valueOf(detectionEntity.getDetectionTime().toLocalTime()));
        return response;
    }

    public DetectionEntity requestToEntity(DetectionRequest detectionRequest) {
        DetectionEntity entity = new DetectionEntity();
        entity.setUserId(detectionRequest.getUserDetection());
        entity.setDetectionTime(detectionRequest.getDetectedTime().minusHours(7));
        entity.setLicensePlateFound(detectionRequest.getLicensePlateFound());
        entity.setEvidenceImg(detectionRequest.getEvidenceImg());
        return entity;
    }
}
