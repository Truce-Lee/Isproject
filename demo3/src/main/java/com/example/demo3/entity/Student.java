package com.example.demo3.entity;


import lombok.Data;


@Data

public class Student {
    private Integer student_id;
    private String name;
    private Integer age;
    private String sex;
    private String email;
    private String phone_number;
}