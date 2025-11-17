package com.zjgsu.course.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * 课程实体类
 */
public class Course {
    private String id;

    @NotBlank(message = "Course code is required")
    private String code;          // 课程代码，如 CS101

    @NotBlank(message = "Course title is required")
    private String title;          // 课程标题

    @NotNull(message = "Instructor is required")
    @Valid
    private Instructor instructor; // 授课教师

    @NotNull(message = "Schedule is required")
    @Valid
    private ScheduleSlot schedule; // 上课时间安排

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;      // 容量（最大学生数）

    private Integer enrolled;      // 已选课学生数

    public Course() {
        this.id = UUID.randomUUID().toString();
        this.enrolled = 0;
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
}
