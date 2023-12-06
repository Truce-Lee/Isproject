package com.example.demo3.service;

import com.example.demo3.entity.Student;
import com.example.demo3.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    public int save(Student student){
        if (student.getStudent_id() == null){
            return  studentMapper.insert(student);
        }else {
            return  studentMapper.update(student);
        }
    }
}
