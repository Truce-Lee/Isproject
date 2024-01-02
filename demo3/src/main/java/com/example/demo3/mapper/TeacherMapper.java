package com.example.demo3.mapper;

import com.example.demo3.entity.Student;
import com.example.demo3.entity.Teacher;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface TeacherMapper {
    @Select("select * from teacher where name = #{name} order by teacher_id desc")
    Teacher selectByTeachername(String name);
    @Select("select * from teacher where teacher_id = #{teacher_id} order by teacher_id desc")
    Teacher selectByTeacher_id(Integer teacher_id);
    @Insert("insert into teacher(name,email,password,role) VALUES (#{name},#{email},#{password},#{role})")
    int insert(Teacher teacher);
    @Update("update teacher set name = #{name},email = #{email}, password = #{password} where teacher_id = #{teacher_id}")
    void updateTeacher(Teacher teacher);
    @Update("update teacher set  password = #{password} where teacher_id = #{teacher_id}")
    void updateTeacherPassword(Teacher teacher);
    @Select("SELECT COUNT(*) FROM teacher ")
    Integer selectTotal();

    @Select("Select * from teacher  limit #{pageSize} offset #{pageNum}")
    List<Teacher> selectPage(Integer pageNum, Integer pageSize);
    @Delete("delete from teacher where teacher_id =#{teacher_id}")
    Integer deleteById(@Param("teacher_id") Integer teacher_id);

    int update(Teacher teacher);
}
