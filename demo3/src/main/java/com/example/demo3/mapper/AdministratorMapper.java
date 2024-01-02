package com.example.demo3.mapper;

import com.example.demo3.entity.Administrator;
import com.example.demo3.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdministratorMapper {
    @Select("select * from administrator where name = #{name} order by administrator_id desc")
    Administrator selectByAdministratorname(String name);
    @Select("select * from administrator where administrator_id = #{administrator_id} order by administrator_id desc")
    Administrator selectByAdministrator_id(Integer administrator_id);
    @Insert("insert into administrator(name,password) VALUES (#{name},#{password})")
    int insert(Administrator administrator);
    @Update("update administrator set name = #{name}, password = #{password} where administrator_id = #{administrator_id}")
    void updateAdministrator(Administrator administrator);
    @Update("update administrator set  password = #{password} where administrator_id = #{administrator_id}")
    void updateAdministratorPassword(Administrator administrator);
}
