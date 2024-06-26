package com.example.final_project.repository.detection;

import com.example.final_project.model.detection.DetectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DetectionRepository extends JpaRepository<DetectionEntity, Long> {

    List<DetectionEntity> findAllByUserId(Long userId);

    DetectionEntity findOneById(Long id);

    @Query(value = "SELECT * FROM detection d WHERE d.license_plate_found = :licensePlateFound ORDER BY d.id DESC LIMIT 1", nativeQuery = true)
    DetectionEntity findTopByLicensePlateFoundOrderByIdDesc(@Param("licensePlateFound") String licensePlateFound);
}
