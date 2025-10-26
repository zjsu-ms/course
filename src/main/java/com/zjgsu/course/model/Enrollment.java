package com.zjgsu.course.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 选课记录实体类
 */
public class Enrollment {
    private String id;
    private String courseId;      // 课程ID
    private String studentId;     // 学生ID
    private LocalDateTime enrolledAt; // 选课时间

    public Enrollment() {
        this.id = UUID.randomUUID().toString();
        this.enrolledAt = LocalDateTime.now();
    }

    public Enrollment(String courseId, String studentId) {
        this();
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
