package com.example.demo3.controller;

import com.example.demo3.entity.Student;
import com.example.demo3.entity.Teacher;
import com.example.demo3.entity.User;
import com.example.demo3.mapper.StudentMapper;
import com.example.demo3.mapper.TeacherMapper;
import com.example.demo3.service.StudentService;
import com.example.demo3.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")

public class TeacherController {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Teacher> data = teacherMapper.selectPage(pageNum, pageSize);
        Integer total = teacherMapper.selectTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
    @DeleteMapping("/{teacher_id}")
    public Integer delete(@PathVariable Integer teacher_id) {
        teacherMapper.deleteById(teacher_id);
        return teacherMapper.deleteById(teacher_id);
    }
    @PostMapping("/change")
    public Integer save(@RequestBody User user) {
        return teacherService.change(user);
//        return studentService.insert(student);
    }
}
