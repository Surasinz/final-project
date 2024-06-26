package com.example.final_project.repository.appeal;

import com.example.final_project.model.appeal.AppealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppealRepository extends JpaRepository<AppealEntity, Long> {
    AppealEntity findOneById (Long id);
    void deleteById(Long id);
}
