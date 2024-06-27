package com.example.final_project.controller.user;

import com.example.final_project.business.detection.DetectionBusiness;
import com.example.final_project.converter.detection.DetectionConverter;
import com.example.final_project.model.detection.DetectionEntity;
import com.example.final_project.model.detection.DetectionRequest;
import com.example.final_project.model.detection.DetectionResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import com.example.final_project.repository.detection.DetectionRepository;
import com.example.final_project.repository.user.UserRepository;
import com.example.final_project.service.FirebaseService;
import com.example.final_project.validator.DetectionValidator;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DetectionController {
    @Autowired
    private DetectionResponse detectionResponse;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetectionConverter detectionConverter;

    @Autowired
    private DetectionRepository detectionRepository;

    @Autowired
    private DetectionValidator detectionValidator;

    @Autowired
    private DetectionRequest detectionRequest;
    @Autowired
    private DetectionBusiness detectionBusiness;
    @Autowired
    private FirebaseService firebaseService;


    @GetMapping("/api/detection")
    public List<DetectionResponse> getUserByUserID(@RequestParam Long userId) {
        List<DetectionEntity> detectionEntity = detectionRepository.findAllByUserId(userId);
        String name = userRepository.findNameByUserId(userId);
        return detectionConverter.entitiesToResponses(detectionEntity, name);
    }

    @GetMapping("/api/detection/all")
    public List<DetectionResponse> getAllDetections() {
        List<DetectionEntity> detectionEntities = detectionRepository.findAll();
        return detectionEntities.stream()
                .map(detectionEntity -> {
                    Long userId = detectionEntity.getUserId();
                    String name = userRepository.findNameByUserId(userId);
                    String faculty = userRepository.findFacultyByUserId(userId);
                    return detectionConverter.entityToFacultyResponse(detectionEntity, name, faculty);
                })
                .collect(Collectors.toList());
    }
    @DeleteMapping("/api/detection")
    public DetectionResponse deleteDetectionsByDetectionId(@RequestParam Long id) throws Exception {
        DetectionEntity detectionEntity = detectionRepository.findOneById(id);
        String name = userRepository.findNameByUserId(detectionEntity.getUserId());
        detectionValidator.validateNullDetectionEntity(detectionEntity);
        detectionRepository.deleteById(id);
        return detectionConverter.entityToResponse(detectionEntity,name);
    }

    @PostMapping("/api/detection")
    public DetectionResponse newDetected(DetectionRequest detectionRequest) throws Exception {
        List<QueryDocumentSnapshot> documents = firebaseService.fetchDataFromFirestore("detections");
        QueryDocumentSnapshot latestDocument = documents.get(0);
        String recognition = detectionBusiness.reformatRecognition(latestDocument.getString("recognition"));
        String imgbbUrl = latestDocument.getString("imgbb_url");
        ZonedDateTime detectionTimestamp = latestDocument.getTimestamp("detection").toDate().toInstant().atZone(ZoneId.of("Asia/Bangkok"));
        LocalDateTime detection = detectionTimestamp.toLocalDateTime();
        Long userId = userRepository.findUserIdByLicensePlate(recognition);
        detectionRequest.setLicensePlateFound(recognition);
        detectionRequest.setEvidenceImg(imgbbUrl);
        detectionRequest.setUserDetection(userId);
        detectionRequest.setDetectedTime(detection);
        DetectionEntity oldDetectionEntity = detectionRepository.findTopByLicensePlateFoundOrderByIdDesc(recognition);
        DetectionEntity detectionEntity = detectionConverter.requestToEntity(detectionRequest);
        String name = userRepository.findNameByUserId(detectionRequest.getUserDetection());
        detectionValidator.validateAndSaveIfNeeded(oldDetectionEntity, detectionEntity);
        detectionRepository.save(detectionEntity);
        return detectionConverter.entityToResponse(detectionEntity, name);
    }
}
