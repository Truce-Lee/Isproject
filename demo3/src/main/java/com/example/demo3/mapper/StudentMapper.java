package com.example.demo3.mapper;

import com.example.demo3.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student")
    List<Student> findall();

    @Insert("insert into student(student_id,name,age,sex,email,phone_number) VALUES (#{student_id},#{name},#{age},#{sex},#{email},#{phone_number})")
    Integer insert(Student student);

    @Update("update student set name = #{name}, age= #{age}, sex =#{sex}, email= #{email}, phone_number=#{phone_number} where student_id = #{student_id}")
    int update(Student student);
}
