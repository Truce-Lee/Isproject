package com.example.demo3.entity;
import lombok.Data;
@Data

public class Administrator extends User{
    private Integer administrator_id;
    private String name;
    private String password;
    private String role;
}
