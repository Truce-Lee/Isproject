package com.example.demo3.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo3.entity.Administrator;
import com.example.demo3.entity.Student;
import com.example.demo3.entity.Teacher;
import com.example.demo3.exception.ServiceException;
import com.example.demo3.mapper.AdministratorMapper;
import com.example.demo3.mapper.StudentMapper;
import com.example.demo3.mapper.TeacherMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.method.HandlerMethod;

public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private AdministratorMapper administratormapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            token = request.getParameter("token");
        }
        if(handler instanceof HandlerMethod){
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if(annotation !=null){
                return  true;
            }
        }
        if(StrUtil.isBlank(token)){
            throw new ServiceException("401","Please log in");
        }
        String id;
        try{
            id = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException("401","Please log in");
        }
        Teacher teacher = teacherMapper.selectByTeacher_id(Integer.valueOf(id));
        Student student = studentMapper.selectByStudent_id(Integer.valueOf(id));
        Administrator administrator = administratormapper.selectByAdministrator_id(Integer.valueOf(id));
        if (teacher == null && student ==null && administrator ==null){
            System.out.println(student);
            throw new ServiceException("4401","Please log in");

        }
        JWTVerifier jwtVerifier;
        if(teacher != null) {
             jwtVerifier = JWT.require(Algorithm.HMAC256(teacher.getPassword())).build();
        }else if (student != null){
             jwtVerifier = JWT.require(Algorithm.HMAC256(student.getPassword())).build();
        }else {
             jwtVerifier = JWT.require(Algorithm.HMAC256(administrator.getPassword())).build();
        }
        try{
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new ServiceException("401","Please log in");
        }
        return  true;
    }
}
