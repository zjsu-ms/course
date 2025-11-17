package com.zjgsu.course.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 选课记录实体类
 */
@Entity
@Table(name = "enrollments",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_course_student", columnNames = {"course_id", "student_id"})
    },
    indexes = {
        @Index(name = "idx_course_id", columnList = "course_id"),
        @Index(name = "idx_student_id", columnList = "student_id"),
        @Index(name = "idx_status", columnList = "status")
    }
)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "course_id", nullable = false)
    private String courseId;      // 课程ID

    @Column(name = "student_id", nullable = false)
    private String studentId;     // 学生ID

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EnrollmentStatus status; // 选课状态

    @Column(name = "enrolled_at", nullable = false, updatable = false)
    private LocalDateTime enrolledAt; // 选课时间

    public Enrollment() {
        this.status = EnrollmentStatus.ACTIVE;
    }

    @PrePersist
    protected void onCreate() {
        this.enrolledAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = EnrollmentStatus.ACTIVE;
        }
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

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}
