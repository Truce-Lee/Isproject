package com.example.demo3.controller;

import com.example.demo3.entity.Student;
import com.example.demo3.mapper.StudentMapper;
import com.example.demo3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Integer save(@RequestBody Student student){
       return studentService.save(student);

    }
    @GetMapping
    public List<Student> index(){

        return studentMapper.findall();

    }
}
