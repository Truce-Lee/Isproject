package com.example.demo3.entity;

import lombok.Data;


@Data
public class Teacher extends User {
    private Integer teacher_id;
    private String name;
    private String email;
    private String password;
    private String role;

    private String token;


}
