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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScoreConverter {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public UserScoreResponse entityToResponse(UserScoreEntity userScoreEntity,String name) {
        UserScoreResponse response = new UserScoreResponse();
        response.setName(name);
        response.setScore(userScoreEntity.getScore());
        return response;
    }


    public UserScoreResponseDate entityToDateResponse(UserScoreEntity userScoreEntity, List<DetectionEntity> detectionEntities, String name) {
        UserScoreResponseDate response = new UserScoreResponseDate();
        response.setName(name);
        response.setScore(userScoreEntity.getScore());

        List<UserScoreResponseDate.DetectionDetail> detectionDetails = detectionEntities.stream()
                .map(detectionEntity -> {
                    UserScoreResponseDate.DetectionDetail detail = new UserScoreResponseDate.DetectionDetail();
                    detail.setDate(detectionEntity.getDetectionTime().getDayOfMonth());
                    detail.setMonth(detectionEntity.getDetectionTime().getMonthValue());
                    detail.setDay(String.valueOf(detectionEntity.getDetectionTime().getDayOfWeek()));
                    detail.setMonthName(String.valueOf(detectionEntity.getDetectionTime().getMonth()));
                    detail.setTime(detectionEntity.getDetectionTime().toLocalTime().format(TIME_FORMATTER));
                    detail.setLicensePlate(detectionEntity.getLicensePlateFound());
                    detail.setEvident(detectionEntity.getEvidenceImg());
                    return detail;
                })
                .collect(Collectors.toList());

        response.setDetections(detectionDetails);
        return response;
    }

    public List<UserScoreResponseDate> entitiesToDateResponses(List<UserScoreEntity> userScoreEntities, List<DetectionEntity> detectionEntities, String name) {
        return userScoreEntities.stream()
                .map(userScoreEntity -> {
                    List<DetectionEntity> matchingDetectionEntities = detectionEntities.stream()
                            .filter(detectionEntity -> detectionEntity.getUserId().equals(userScoreEntity.getUserId()))
                            .collect(Collectors.toList());
                    return entityToDateResponse(userScoreEntity, matchingDetectionEntities, name);
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
