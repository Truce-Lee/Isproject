package com.example.demo3;

import com.example.demo3.entity.Student;
import com.example.demo3.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController

public class Demo3Application {

	@Autowired
	private StudentMapper studentMapper;

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

//	@GetMapping("/")
//	public List<Student> index(){
//
//		return studentMapper.findall();
//
//	}

}
