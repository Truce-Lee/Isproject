package com.example.demo3.service;

import cn.hutool.core.util.RandomUtil;
import com.example.demo3.common.PasswordEncryptor;
import com.example.demo3.entity.Student;
import com.example.demo3.entity.Teacher;
import com.example.demo3.entity.User;
import com.example.demo3.exception.ServiceException;
import com.example.demo3.mapper.StudentMapper;
import com.example.demo3.mapper.TeacherMapper;
import com.example.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;
    public Teacher login(User user) {
        Teacher dbteacher = teacherMapper.selectByTeachername(user.getName());
        if(dbteacher == null){
            throw new ServiceException("There is no account");
        }
        String encryptedPassword = PasswordEncryptor.encryptPassword(user.getPassword());
        if(!encryptedPassword.equals(dbteacher.getPassword())){
            throw new ServiceException("The account or password is wrong");
        }
        String token = TokenUtils.createToken(dbteacher.getTeacher_id().toString(),dbteacher.getPassword());
        dbteacher.setToken(token);
        return dbteacher;
    }

    public Teacher register(User user) {
        Teacher dbteacher = teacherMapper.selectByTeachername(user.getName());
        if(dbteacher != null){
            throw new ServiceException("There is already an account");
        }
        Teacher teacher = new Teacher();
        teacher.setName(user.getName());
        String originalPassword = user.getPassword();
        String encryptedPassword = PasswordEncryptor.encryptPassword(originalPassword);
        teacher.setPassword(encryptedPassword);
        teacher.setRole("teacher");
        teacherMapper.insert(teacher);
        return teacher;
    }

    public void resetPassword(User user) {
        Teacher dbteacher = teacherMapper.selectByTeachername(user.getName());
        System.out.println(user);
        if(dbteacher == null){
            throw new ServiceException("There is no account");
        }
        if(!user.getEmail().equals(dbteacher.getEmail())){
            throw new ServiceException("Validation error");
        }

        Teacher teacher = new Teacher();
        String originalPassword = user.getPassword();
        String encryptedPassword = PasswordEncryptor.encryptPassword(originalPassword);
        teacher.setTeacher_id(dbteacher.getTeacher_id());
        teacher.setPassword(encryptedPassword);
        teacherMapper.updateTeacherPassword(teacher);
    }
    public int save(Teacher teacher){
        System.out.println(teacher.getTeacher_id());
        if (teacher.getTeacher_id() == null){

            return  teacherMapper.insert(teacher);
        }else {
            return  teacherMapper.update(teacher);
        }
    }

    public Integer change(User user) {
        if (user.getName() == null){
            throw new ServiceException("null");
        }
        Student dbstudent = studentMapper.selectByStudentname(user.getName());
        System.out.println(user.getName());
        Teacher teacher = new Teacher();
        teacher.setName(dbstudent.getName());
        teacher.setEmail(dbstudent.getEmail());
        teacher.setRole("teacher");
        teacher.setPassword(dbstudent.getPassword());
        System.out.println(teacher);
        teacherMapper.insert(teacher);
        return 1;
    }
}
