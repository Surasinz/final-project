package com.example.final_project.converter.appeal;

import com.example.final_project.model.appeal.AppealResponse;
import com.example.final_project.model.detection.DetectionEntity;
import org.springframework.stereotype.Component;

@Component
public class AppealConverter {
    public AppealResponse detectionEntityToResponse(DetectionEntity detectionEntity, Long appealId) {
        AppealResponse response = new AppealResponse();
        response.setDetectedTime(detectionEntity.getDetectionTime());
        response.setEvidenceImage(detectionEntity.getEvidenceImg());
        response.setLicensePlate(detectionEntity.getLicensePlateFound());
        response.setDetectionId(detectionEntity.getId());
        response.setAppealId(appealId);
        return response;
    }
}
