package com.example.final_project.model.userScore;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(schema = "public",name = "user_score")
public class UserScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "score")
    private Integer score;
}
