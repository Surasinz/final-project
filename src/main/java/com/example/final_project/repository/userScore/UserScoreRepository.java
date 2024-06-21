package com.example.final_project.repository.userScore;


import com.example.final_project.model.userScore.UserScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<UserScoreEntity, Long> {
    UserScoreEntity findByUserId (Long userId);

    List<UserScoreEntity> findAllByUserId(Long userId);
}
