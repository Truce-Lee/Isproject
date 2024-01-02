package com.example.demo3.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class StudentService{

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    public int insert(Student student){
        return  studentMapper.insert(student);
    }
    public int save(Student student){
        System.out.println(student.getStudent_id());
        if (student.getStudent_id() == null){

            return  studentMapper.insert(student);
        }else {
            return  studentMapper.update(student);
        }
    }
    public Student login(User user) {
        Student dbstudent = studentMapper.selectByStudentname(user.getName());
        if(dbstudent == null){
            throw new ServiceException("There is no account");
        }
        String encryptedPassword = PasswordEncryptor.encryptPassword(user.getPassword());
        if(!encryptedPassword.equals(dbstudent.getPassword())){
            throw new ServiceException("The account or password is wrong");
        }
        String token = TokenUtils.createToken(dbstudent.getStudent_id().toString(),dbstudent.getPassword());
        dbstudent.setToken(token);
        return dbstudent;
    }

    public Student register(User user) {
        Student dbstudent = studentMapper.selectByStudentname(user.getName());
        if(dbstudent != null){
            throw new ServiceException("There is already an account");
        }
        Student student = new Student();
        student.setName(user.getName());
        String originalPassword = user.getPassword();
        String encryptedPassword = PasswordEncryptor.encryptPassword(originalPassword);
        student.setPassword(encryptedPassword);
        student.setRole("student");
        studentMapper.insert(student);
        return student;
    }
    public void resetPassword(User user) {
        Student dbstudent = studentMapper.selectByStudentname(user.getName());
        if(dbstudent == null){
            throw new ServiceException("There is no account");
        }
        if(!user.getEmail().equals(dbstudent.getEmail())){
            throw new ServiceException("Validation error");
        }
        Student student = new Student();
        String originalPassword = user.getPassword();
        String encryptedPassword = PasswordEncryptor.encryptPassword(originalPassword);
        student.setStudent_id(dbstudent.getStudent_id());
        student.setPassword(encryptedPassword);
        studentMapper.updateStudentPassword(student);
    }
    public Integer change(User user) {
        if (user.getName() == null){
            throw new ServiceException("null");
        }
        Teacher dbteacher = teacherMapper.selectByTeachername(user.getName());
        Student student = new Student();
        student.setName(dbteacher.getName());
        student.setEmail(dbteacher.getEmail());
        student.setRole("student");
        student.setPassword(dbteacher.getPassword());
        System.out.println(student);
        studentMapper.insert(student);
        return 1;
    }
}

