package com.example.final_project.converter.user;

import com.example.final_project.model.user.UserEntity;
import com.example.final_project.model.user.UserLoginResponse;
import com.example.final_project.model.user.UserRegisterRequest;
import com.example.final_project.model.user.UserRegisterResponse;
import com.example.final_project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private UserRepository userRepository;

    public UserLoginResponse entityToResponse(UserEntity userEntity) {
        UserLoginResponse response = new UserLoginResponse();
        response.setStudentID(userEntity.getStudentID());
        response.setName(userEntity.getName());
        response.setRole(userEntity.getRole());
        response.setUserId(userEntity.getUserId());
        return response;
    }
    public UserEntity requestToEntity(UserRegisterRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setStudentID(userRequest.getStudentID());
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setField(userRequest.getField());
        userEntity.setFaculty(userRequest.getFaculty());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setSurName(userRequest.getSurName());
        userEntity.setGender(userRequest.getGender());
        userEntity.setProfile(userRequest.getProfile());
        userEntity.setLicensePlate(userRequest.getLicensePlate());
        userEntity.setRole("user");
        return userEntity;
    }

    public UserRegisterResponse entityToRegisterResponse(UserEntity userEntity) {
        UserRegisterResponse response = new UserRegisterResponse();
        response.setName(userEntity.getName());
        response.setSurName(userEntity.getSurName());
        response.setEmail(userEntity.getEmail());
        response.setField(userEntity.getField());
        response.setFaculty(userEntity.getFaculty());
        response.setGender(userEntity.getGender());
        response.setPassword(userEntity.getPassword());
        return response;
    }
}
