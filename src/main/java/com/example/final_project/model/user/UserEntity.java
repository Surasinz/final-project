package com.example.final_project.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(schema = "public",name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "sur_name")
    private String surName;
    @Column(name = "student_id")
    private String studentID;
    @Column(name = "password")
    private String password;
    @Column(name = "gender")
    private String gender;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "field")
    private String major;
    @Column(name = "email")
    private String email;
    @Column(name = "profile")
    private String profile;
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "role")
    private String role;
}
