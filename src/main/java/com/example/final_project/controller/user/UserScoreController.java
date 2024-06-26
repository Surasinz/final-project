package com.example.final_project.controller.user;

import com.example.final_project.converter.userScore.UserScoreConverter;
import com.example.final_project.model.detection.DetectionEntity;
import com.example.final_project.model.userScore.UserScoreEntity;
import com.example.final_project.model.userScore.UserScoreRequest;
import com.example.final_project.model.userScore.UserScoreResponse;
import com.example.final_project.model.userScore.UserScoreResponseDate;
import com.example.final_project.repository.detection.DetectionRepository;
import com.example.final_project.repository.user.UserRepository;
import com.example.final_project.repository.userScore.UserScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UserScoreController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserScoreRepository userScoreRepository;
    @Autowired
    UserScoreConverter userScoreConverter;
    @Autowired
    DetectionRepository detectionRepository;
    @GetMapping("/api/userScore")
    public List<UserScoreResponseDate> getAllUserById(@RequestParam Long userId) {
        String name = userRepository.findNameByUserId(userId);
        List<UserScoreEntity> userScoreEntities = userScoreRepository.findAllByUserId(userId);
        List<DetectionEntity> detectionEntities = detectionRepository.findAllByUserId(userId);
        return userScoreConverter.entitiesToDateResponses(userScoreEntities, detectionEntities, name);
    }
    @PatchMapping("/api/userScore/update")
    public UserScoreResponse updateScoreByUserId(@RequestParam Long userId){
        UserScoreEntity userScoreEntity = userScoreRepository.findByUserId(userId);
        List<DetectionEntity> detectionEntities = detectionRepository.findAllByUserId(userId);
        Integer score = 100-detectionEntities.size();
        UserScoreEntity updatedUserScoreEntity = userScoreConverter.requestToEntity(score,userScoreEntity);
        userScoreRepository.save(updatedUserScoreEntity);
        String name = userRepository.findNameByUserId(userId);
        return userScoreConverter.entityToResponse(updatedUserScoreEntity,name);
    }
}
