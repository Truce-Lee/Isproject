package com.example.demo3.mapper;

import com.example.demo3.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface CourseMapper {
    @Select("SELECT * FROM course")
    List<Course> findall(); 

    @Insert("insert into course(name,start_time,finish_time,description,activity_time,syllabus) VALUES (#{name},#{start_time},#{finish_time},#{description},#{activity_time},#{syllabus})")
    int insert(Course course);


//    @Insert("insert into student(name,start_time,finish_time,description) VALUES (#{name},#{start_time},#{finish_time},#{description})")
    int update(Course course);


    @Delete("delete from course where course_id =#{course_id}")
    Integer deleteById(@Param("course_id") Integer course_id);

    @Select("Select * from course where name like '%' || #{name} || '%' limit #{pageSize} offset #{pageNum}")
    List<Course> selectPage(Integer pageNum, Integer pageSize, String name);

    @Select("SELECT COUNT(*) FROM course where name like '%' || #{name} || '%'")
    Integer selectTotal(String name);
}
