package com.example.final_project.validator;

import com.example.final_project.model.user.UserEntity;
import org.springframework.stereotype.Component;

import java.lang.Exception;

@Component
public class UserValidator {
    public void validateUserLoginAndPassword(UserEntity userEntity, String password) throws Exception {
        if (userEntity == null) {
            throw new Exception("User Not found");
        }
        else if (!(userEntity.getPassword().equals(password))) {
            throw new Exception("Wrong password");
        }
    }
    public void validateUserRegister(UserEntity userEntity, String plate) throws Exception {
        if (userEntity != null && userEntity.getStudentID() != null) {
            throw new Exception("Duplicate StudentID");
        }
        if (userEntity != null && !userEntity.getLicensePlate().equals(plate)) {
            throw new Exception("Duplicate license plate");
        }
    }
}
