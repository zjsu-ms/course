package com.zjgsu.course.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 学生实体类
 */
public class Student {
    private String id;
    private String studentId;     // 学号，如 2024001
    private String name;          // 姓名
    private String major;         // 专业
    private Integer grade;        // 年级，如 2024
    private String email;         // 邮箱
    private LocalDateTime createdAt; // 创建时间

    public Student() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public Student(String studentId, String name, String major, Integer grade, String email) {
        this();
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
