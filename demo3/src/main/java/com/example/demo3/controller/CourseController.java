package com.example.demo3.controller;

import com.example.demo3.entity.Course;
import com.example.demo3.mapper.CourseMapper;
import com.example.demo3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;

    @PostMapping
//    新增修改
    public Integer save(@RequestBody Course course) {
        return courseService.save(course);
//        return studentService.insert(student);
    }

    @GetMapping
//    查询
    public List<Course> index() {

        return courseMapper.findall();

    }

    @DeleteMapping("/{course_id}")
    public Integer delete(@PathVariable Integer course_id) {
        courseMapper.deleteById(course_id);
        return courseMapper.deleteById(course_id);
    }

    //    分特查询
//    接口路径:/student/page?pageNum=1&pageSize=10
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Course> data = courseMapper.selectPage(pageNum, pageSize);
        Integer total = courseMapper.selectTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

}
