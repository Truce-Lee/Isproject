package com.example.demo3.controller;

import com.example.demo3.entity.Student;
import com.example.demo3.entity.User;
import com.example.demo3.mapper.StudentMapper;
import com.example.demo3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
//    新增修改
    public Integer save(@RequestBody Student student) {
        return studentService.save(student);
//        return studentService.insert(student);
    }

    @GetMapping
//    查询
    public List<Student> index() {

        return studentMapper.findall();

    }

    @DeleteMapping("/{student_id}")
    public Integer delete(@PathVariable Integer student_id) {
        studentMapper.deleteById(student_id);
        return studentMapper.deleteById(student_id);
    }

//    分特查询
//    接口路径:/student/page?pageNum=1&pageSize=10
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Student> data = studentMapper.selectPage(pageNum, pageSize);
        Integer total = studentMapper.selectTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @PostMapping("/change")
    public Integer save(@RequestBody User user) {
        return studentService.change(user);
//        return studentService.insert(student);
    }

}

