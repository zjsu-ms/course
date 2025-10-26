package com.zjgsu.course.model;

import java.util.UUID;

/**
 * 课程实体类
 */
public class Course {
    private String id;
    private String code;          // 课程代码，如 CS101
    private String title;          // 课程标题
    private Instructor instructor; // 授课教师
    private ScheduleSlot schedule; // 上课时间安排
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
