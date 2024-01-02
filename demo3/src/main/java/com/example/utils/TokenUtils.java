package com.example.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo3.entity.Teacher;
import com.example.demo3.mapper.TeacherMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component

public class TokenUtils {
//    private static TeacherMapper staticteacherMapper;
//    @Resource
//    TeacherMapper teacherMapper;
//
//    @PostConstruct
//    public void setTeacherService(){
//        staticteacherMapper = teacherMapper;
//    }
//    public static String createToken(String teacher_id, String sign){
//        return JWT.create().withAudience(teacher_id)
//                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
//                .sign(Algorithm.HMAC256(sign));
//    }
//    public static Teacher getCurrentTeacher(){
//        try {
//            jakarta.servlet.http.HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            String token = request.getHeader("token");
//            if (StrUtil.isNotBlank(token)){
//                String teacher_id = JWT.decode(token).getAudience().get(0);
//                return staticteacherMapper.selectByTeacher_id(Integer.valueOf(teacher_id));
//            }
//
//        }catch (Exception e){
//            return null;
//        }
//        return null;
//    }
private static TeacherMapper staticteacherMapper;
    @Resource
    TeacherMapper teacherMapper;

    @PostConstruct
    public void setTeacherService(){
        staticteacherMapper = teacherMapper;
    }
    public static String createToken(String teacher_id, String sign){
        return JWT.create().withAudience(teacher_id)
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(sign));
    }
    public static Teacher getCurrentTeacher(){
        try {
            jakarta.servlet.http.HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String teacher_id = JWT.decode(token).getAudience().get(0);
                return staticteacherMapper.selectByTeacher_id(Integer.valueOf(teacher_id));
            }

        }catch (Exception e){
            return null;
        }
        return null;
    }

}
