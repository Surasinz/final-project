package com.example.final_project.controller.user;


import com.example.final_project.model.appeal.AppealEntity;
import com.example.final_project.model.appeal.AppealRequest;
import com.example.final_project.model.appeal.AppealResponse;
import com.example.final_project.model.detection.DetectionEntity;
import com.example.final_project.converter.appeal.AppealConverter;
import com.example.final_project.model.detection.DetectionResponse;
import com.example.final_project.repository.appeal.AppealRepository;
import com.example.final_project.repository.detection.DetectionRepository;
import com.example.final_project.validator.AppealValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AppealController {
    @Autowired
    AppealRepository appealRepository;
    @Autowired
    DetectionRepository detectionRepository;
    @Autowired
    AppealConverter appealConverter;
    @Autowired
    AppealValidator appealValidator;

    @GetMapping("/api/appeal")
    public List<AppealResponse> getAllAppeal() {
        List<AppealEntity> appealEntities = appealRepository.findAll();
        return appealEntities.stream()
                .map(appealEntity -> {
                    DetectionEntity detectionEntity = detectionRepository.findById(appealEntity.getDetectionId())
                            .orElseThrow(() -> new RuntimeException("Detection not found"));
                    return appealConverter.detectionEntityToResponse(detectionEntity, appealEntity.getId());
                })
                .collect(Collectors.toList());
    }
    @DeleteMapping("/api/appeal")
    public void deleteAppealByAppealId(@RequestParam Long id) throws Exception {
        AppealEntity appealEntity = appealRepository.findOneById(id);
        appealValidator.validateNullAppealEntity(appealEntity);
        appealRepository.deleteById(id);
    }
    @PostMapping("/api/appeal")
    public void createAppeal(@RequestBody AppealRequest appealRequest){
        AppealEntity appealEntity = appealConverter.requestToEntity(appealRequest);
        appealRepository.save(appealEntity);
    }
}
