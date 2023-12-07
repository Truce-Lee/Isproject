package com.example.demo3.entity;

import lombok.Data;


@Data

public class Course {
    private Integer course_id;
    private String name;
    private String start_time;
    private String finish_time;
    private String description;
    private Integer activity_time;
}
