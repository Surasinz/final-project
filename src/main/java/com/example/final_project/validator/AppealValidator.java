package com.example.final_project.validator;

import com.example.final_project.model.appeal.AppealEntity;
import org.springframework.stereotype.Component;

@Component
public class AppealValidator {
    public void validateNullAppealEntity(AppealEntity appealEntity) throws Exception {
        if (appealEntity == null) {
            throw new Exception("can't find appeal id");
        }
    }
}


