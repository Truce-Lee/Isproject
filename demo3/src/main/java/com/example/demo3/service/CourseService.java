package com.example.demo3.service;

import com.example.demo3.entity.Course;
import com.example.demo3.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public int insert(Course course){
        return  courseMapper.insert(course);
    }
    public int save(Course course){
        System.out.println(course.getCourse_id());
        if (course.getCourse_id() == null){

            return  courseMapper.insert(course);
        }else {
            return  courseMapper.update(course);
        }
    }
}
