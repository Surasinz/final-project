package com.example.final_project.repository.user;

import com.example.final_project.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByStudentID(String studentID);
    UserEntity findOneByUserId(Long userId);
    @Query("SELECT u.name FROM UserEntity u WHERE u.id = :userId")
    String findNameByUserId(Long userId);
    @Query("SELECT u.faculty FROM UserEntity u WHERE u.id = :userId")
    String findFacultyByUserId(Long userId);
    @Query("SELECT u.userId FROM UserEntity u WHERE u.licensePlate = :licensePlate")
    Long findUserIdByLicensePlate(String licensePlate);
    @Query("SELECT u.licensePlate FROM UserEntity u WHERE u.name = :name")
    String findLicensePlateByName(String name);

}
