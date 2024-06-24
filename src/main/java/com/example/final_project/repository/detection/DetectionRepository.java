package com.example.final_project.repository.detection;

import com.example.final_project.model.detection.DetectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public interface DetectionRepository extends JpaRepository<DetectionEntity, Long> {
    List<DetectionEntity> findAllByLicensePlateFound (String licensePlateFound);

    List<DetectionEntity> findAllByUserDetection (Long userId);

    @Query(value = "SELECT d.detectionTime FROM DetectionEntity d WHERE d.userDetection = :userId")
    LocalDateTime findDetectionTimeByLicensePlateFound(String licensePlateFound);

}
