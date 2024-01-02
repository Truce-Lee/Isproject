package com.example.demo3.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.*;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented


public @interface AuthAccess {


}
