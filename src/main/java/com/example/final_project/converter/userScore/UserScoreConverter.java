package com.example.final_project.converter.userScore;

import com.example.final_project.model.detection.DetectionEntity;
import com.example.final_project.model.user.UserEntity;
import com.example.final_project.model.user.UserLoginResponse;
import com.example.final_project.model.userScore.UserScoreEntity;
import com.example.final_project.model.userScore.UserScoreRequest;
import com.example.final_project.model.userScore.UserScoreResponse;
import com.example.final_project.model.userScore.UserScoreResponseDate;
import com.example.final_project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScoreConverter {

    public UserScoreResponse entityToResponse(UserScoreEntity userScoreEntity,String name) {
        UserScoreResponse response = new UserScoreResponse();
        response.setName(name);
        response.setScore(userScoreEntity.getScore());
        return response;
    }



    public UserScoreResponseDate entityToDateResponse(UserScoreEntity userScoreEntity, DetectionEntity detectionEntity) {
        UserScoreResponseDate response = new UserScoreResponseDate();
        response.setDate(detectionEntity.getDetectionTime().getDayOfMonth());
        response.setMonth(detectionEntity.getDetectionTime().getMonthValue());
        response.setDay(String.valueOf(detectionEntity.getDetectionTime().getDayOfWeek()));
        response.setMonthName(String.valueOf(detectionEntity.getDetectionTime().getMonth()));
        response.setLicensePlate(detectionEntity.getLicensePlateFound());
        response.setScore(userScoreEntity.getScore());
        response.setEvident(detectionEntity.getEvidenceImg());
        return response;
    }

    public List<UserScoreResponseDate> entitiesToDateResponses(List<UserScoreEntity> userScoreEntities, List<DetectionEntity> detectionEntities) {
        return userScoreEntities.stream()
                .map(userScoreEntity -> {
                    DetectionEntity matchingDetectionEntity = detectionEntities.stream()
                            .filter(detectionEntity -> detectionEntity.getUserDetection().equals(userScoreEntity.getUserId()))
                            .findFirst()
                            .orElse(null);
                    return matchingDetectionEntity != null ? entityToDateResponse(userScoreEntity, matchingDetectionEntity) : null;
                })
                .collect(Collectors.toList());
    }

    public UserScoreEntity requestToEntity(Integer score ,UserScoreEntity userScoreEntity){
        userScoreEntity.setUserId(userScoreEntity.getUserId());
        userScoreEntity.setScore(score);
        return userScoreEntity;
    }

    public UserScoreEntity registerRequestToEntity(Integer score ,Long userId,UserScoreEntity userScoreEntity ){
        userScoreEntity.setUserId(userId);
        userScoreEntity.setScore(score);
        return userScoreEntity;
    }
}
