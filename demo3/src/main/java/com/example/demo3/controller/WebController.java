package com.example.demo3.controller;
import cn.hutool.core.util.StrUtil;
import com.example.demo3.common.AuthAccess;
import com.example.demo3.common.Result;
import com.example.demo3.entity.Administrator;
import com.example.demo3.entity.Student;
import com.example.demo3.entity.Teacher;
import com.example.demo3.entity.User;
import com.example.demo3.service.AdministratorService;
import com.example.demo3.service.StudentService;
import com.example.demo3.service.TeacherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    @Resource
    TeacherService teacherService;
    @Resource
    StudentService studentService;
    @Resource
    AdministratorService administratorService;

    @AuthAccess
    @GetMapping("/")
    public Result hello() {
        return Result.success("success");
    }


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (StrUtil.isBlank(user.getName()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("Invalid Input");
        }
        if (user.getRole().equals("teacher")) {
            Teacher teacher = teacherService.login(user);
            return Result.success(teacher);
        } else if (user.getRole().equals("student")) {
            Student student = studentService.login(user);
            return Result.success(student);
        } else if (user.getRole().equals("administrator")) {
            Administrator administrator = administratorService.login(user);
            return Result.success(administrator);
        } else {
            return Result.error("Invalid role");
        }
    }

    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (StrUtil.isBlank(user.getName()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("Invalid Input");
        }

        if (user.getName().length() > 10 || user.getPassword().length() > 20) {
            return Result.error("The password is out of range");
        }
        if (user.getRole().equals("teacher")) {
            Teacher teacher = teacherService.register(user);
            return Result.success(teacher);
        }
        if (user.getRole().equals("student")) {
            Student student = studentService.register(user);
            return Result.success(student);
        } else {
            return Result.error("Invalid role");
        }
    }

    @AuthAccess
    @PutMapping("/password")
    public Result password(@RequestBody User user) {
        if (StrUtil.isBlank(user.getName())) {
            return Result.error("Invalid Input");
        }
        if (user.getRole().equals("teacher")) {
            teacherService.resetPassword(user);
            return Result.success(user);
        }
        if (user.getRole().equals("student")) {
            studentService.resetPassword(user);
            return Result.success(user);
        } else {
            return Result.error("Invalid Input");
        }
    }
}


