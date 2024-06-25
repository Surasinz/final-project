package com.example.final_project.model.detection;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "public",name = "detection")
public class DetectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "license_plate_found")
    private String licensePlateFound;
    @Column(name = "evidence_img")
    private String evidenceImg;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "detection_time")
    private LocalDateTime detectionTime;
    @Column(name = "user_detection")
    private Long userDetection;
}
