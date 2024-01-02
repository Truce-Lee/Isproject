package com.example.demo3.service;

import com.example.demo3.entity.Administrator;
import com.example.demo3.entity.Student;
import com.example.demo3.entity.Teacher;
import com.example.demo3.entity.User;
import com.example.demo3.exception.ServiceException;
import com.example.demo3.mapper.AdministratorMapper;
import com.example.demo3.mapper.StudentMapper;
import com.example.demo3.mapper.TeacherMapper;
import com.example.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service

public class AdministratorService {
    @Autowired
    AdministratorMapper administratorMapper;
    StudentMapper studentMapper;

    TeacherMapper teacherMapper;
    public Administrator login(User user) {
        Administrator dbadministrator = administratorMapper.selectByAdministratorname(user.getName());
        if(dbadministrator == null){
            throw new ServiceException("There is no account");
        }
        if(!user.getPassword().equals(dbadministrator.getPassword())){
            throw new ServiceException("The account or password is wrong");
        }
        String token = TokenUtils.createToken(dbadministrator.getAdministrator_id().toString(),dbadministrator.getPassword());
        dbadministrator.setToken(token);
        return dbadministrator;
    }


}
