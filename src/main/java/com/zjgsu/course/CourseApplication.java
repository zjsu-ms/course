package com.zjgsu.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 课程管理系统主应用类
 * 
 * 这是一个单体应用，演示了课程管理系统的基本功能：
 * - 课程管理（CRUD操作）
 * - 学生选课管理
 */
@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }
}
