package com.example.final_project.controller.user;

import com.example.final_project.converter.detection.DetectionConverter;
import com.example.final_project.model.detection.DetectionEntity;
import com.example.final_project.model.detection.DetectionRequest;
import com.example.final_project.model.detection.DetectionResponse;
import java.util.stream.Collectors;
import com.example.final_project.repository.detection.DetectionRepository;
import com.example.final_project.repository.user.UserRepository;
import com.example.final_project.service.FirebaseService;
import com.example.final_project.validator.DetectionValidator;
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
    private FirebaseService firebaseService;


    @GetMapping("/api/detection")
    public List<DetectionResponse> getUserByLicensePlateAndProvince(@RequestParam String licensePlate) {
        List<DetectionEntity> detectionEntity = detectionRepository.findAllByLicensePlateFound(licensePlate);
        String name = userRepository.findNameByLicensePlate(licensePlate);
        return detectionConverter.entitiesToResponses(detectionEntity, name);
    }


    @GetMapping("/api/detection/all")
    public List<DetectionResponse> getAllDetections() {
        List<DetectionEntity> detectionEntities = detectionRepository.findAll();
        List<Long> userIds = detectionEntities.stream()
                .map(DetectionEntity::getUserDetection)
                .collect(Collectors.toList());
        List<String> names = userRepository.findAllNamesByUserIds(userIds);
        List<String> faculties = userRepository.findAllFacultiesByNames(names);
        return detectionConverter.allEntitiesToResponses(detectionEntities, names, faculties);
    }

    @PostMapping("/api/detection")
    public DetectionResponse newDetected(DetectionRequest detectionRequest) throws Exception {
        List<QueryDocumentSnapshot> documents = firebaseService.fetchDataFromFirestore("detections");
        QueryDocumentSnapshot latestDocument = documents.get(documents.size() - 1);
        String recognition = latestDocument.getString("recognition");
        String imgbbUrl = latestDocument.getString("imgbb_url");
        Long userId = userRepository.findUserIdByLicensePlate(recognition);
        detectionRequest.setLicensePlateFound(recognition);
        detectionRequest.setEvidenceImg(imgbbUrl);
        detectionRequest.setUserDetection(userId);
        DetectionEntity detectionEntity = detectionConverter.requestToEntity(detectionRequest);
        String name = userRepository.findNameByUserId(detectionRequest.getUserDetection());
        detectionValidator.isTimeValid(detectionRepository.findDetectionTimeByUserDetection(userId));
        //detectionRepository.save(detectionEntity);
        return detectionConverter.entityToResponse(detectionEntity,name);
    }
}
