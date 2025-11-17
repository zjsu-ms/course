package com.zjgsu.course.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * 课程实体类
 */
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Course code is required")
    @Column(unique = true, nullable = false, length = 20)
    private String code;          // 课程代码，如 CS101

    @NotBlank(message = "Course title is required")
    @Column(nullable = false)
    private String title;          // 课程标题

    @NotNull(message = "Instructor is required")
    @Valid
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "instructor_id")),
        @AttributeOverride(name = "name", column = @Column(name = "instructor_name")),
        @AttributeOverride(name = "email", column = @Column(name = "instructor_email"))
    })
    private Instructor instructor; // 授课教师

    @NotNull(message = "Schedule is required")
    @Valid
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "dayOfWeek", column = @Column(name = "schedule_day_of_week")),
        @AttributeOverride(name = "startTime", column = @Column(name = "schedule_start_time")),
        @AttributeOverride(name = "endTime", column = @Column(name = "schedule_end_time")),
        @AttributeOverride(name = "expectedAttendance", column = @Column(name = "schedule_expected_attendance"))
    })
    private ScheduleSlot schedule; // 上课时间安排

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(nullable = false)
    private Integer capacity;      // 容量（最大学生数）

    @Column(nullable = false)
    private Integer enrolled;      // 已选课学生数

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Course() {
        this.enrolled = 0;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.enrolled == null) {
            this.enrolled = 0;
        }
    }

    public Course(String code, String title, Instructor instructor, ScheduleSlot schedule, Integer capacity) {
        this();
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        this.schedule = schedule;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public ScheduleSlot getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleSlot schedule) {
        this.schedule = schedule;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Integer enrolled) {
        this.enrolled = enrolled;
    }

    /**
     * 判断课程是否已满
     */
    public boolean isFull() {
        return enrolled >= capacity;
    }

    /**
     * 增加选课人数
     */
    public void incrementEnrolled() {
        this.enrolled++;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
