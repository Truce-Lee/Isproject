package com.example.demo3.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String email;
    private String phone_number;
    private String password;
    private String role;

    private String token;
}
