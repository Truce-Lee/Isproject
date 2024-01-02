package com.example.demo3.mapper;
import org.apache.ibatis.annotations.*;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo3.entity.Student;
import com.example.demo3.entity.Teacher;

import java.util.List;
//@Mapper
public interface StudentMapper{
    @Select("SELECT * FROM student")
    List<Student> findall();

    @Insert("insert into student(name,age,sex,email,phone_number,password,role) VALUES (#{name},#{age},#{sex},#{email},#{phone_number},#{password},#{role})")
    int insert(Student student);


    int update(Student student);

    @Delete("delete from student where student_id =#{student_id}")
    Integer deleteById(@Param("student_id") Integer student_id);

    @Select("Select * from student  limit #{pageSize} offset #{pageNum}")
    List<Student> selectPage(Integer pageNum, Integer pageSize);

//    @Select("SELECT COUNT(*) FROM student WHERE name like concat('%', #{name}, '%')")
    @Select("SELECT COUNT(*) FROM student ")
    Integer selectTotal();

    @Select("select * from student where name = #{name} order by student_id desc")
    Student selectByStudentname(String name);
    @Select("select * from student where student_id = #{student_id} order by student_id desc")
    Student selectByStudent_id(Integer student_id);

    @Update("update student set name = #{name}, age = #{age},sex = #{sex}, where student_id = #{student_id}")
    void updateStudent(Student student);
    @Update("update student set  password = #{password} where student_id = #{student_id}")
    void updateStudentPassword(Student student);
}
