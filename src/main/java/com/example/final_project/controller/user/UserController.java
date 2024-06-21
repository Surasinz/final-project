package com.example.final_project.controller.user;

import com.example.final_project.converter.user.UserConverter;
import com.example.final_project.converter.userScore.UserScoreConverter;
import com.example.final_project.model.user.UserEntity;
import com.example.final_project.model.user.UserLoginResponse;
import com.example.final_project.model.user.UserRegisterRequest;
import com.example.final_project.model.user.UserRegisterResponse;
import com.example.final_project.model.userScore.UserScoreEntity;
import com.example.final_project.repository.user.UserRepository;
import com.example.final_project.repository.userScore.UserScoreRepository;
import com.example.final_project.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserScoreRepository userScoreRepository;
    @Autowired
    private UserScoreConverter userScoreConverter;

    @GetMapping("/api/user/login")
    public UserLoginResponse getUserByUserNameAndPassword(@RequestParam String studentID, @RequestParam String password) throws Exception {
        UserEntity userEntity = userRepository.findOneByStudentID(studentID);
        userValidator.validateUserLoginAndPassword(userEntity, password);
        return (userConverter.entityToResponse(userEntity));
    }
    @PostMapping("/api/user/register")
    public UserRegisterResponse registerUser(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        UserEntity existingUser = userRepository.findOneByStudentID(userRegisterRequest.getStudentID());
        userValidator.validateUserRegister(existingUser, userRegisterRequest.getLicensePlate());
        UserEntity newUser = userConverter.requestToEntity(userRegisterRequest);
        userRepository.save(newUser);
        UserScoreEntity newUserScore = userScoreConverter.registerRequestToEntity(100,newUser.getUserId(),new UserScoreEntity());
        userScoreRepository.save(newUserScore);
        return userConverter.entityToRegisterResponse(newUser);
    }
}
