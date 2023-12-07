package com.example.demo3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo3.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
//@Mapper
public interface StudentMapper{
    @Select("SELECT * FROM student")
    List<Student> findall();

    @Insert("insert into student(name,age,sex,email,phone_number) VALUES (#{name},#{age},#{sex},#{email},#{phone_number})")
    int insert(Student student);


    int update(Student student);

    @Delete("delete from student where student_id =#{student_id}")
    Integer deleteById(@Param("student_id") Integer student_id);

    @Select("Select * from student  limit #{pageSize} offset #{pageNum}")
    List<Student> selectPage(Integer pageNum, Integer pageSize);

//    @Select("SELECT COUNT(*) FROM student WHERE name like concat('%', #{name}, '%')")
    @Select("SELECT COUNT(*) FROM student ")
    Integer selectTotal();
}
