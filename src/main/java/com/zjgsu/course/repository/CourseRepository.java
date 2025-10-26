package com.zjgsu.course.repository;

import com.zjgsu.course.model.Course;
import com.zjgsu.course.model.Instructor;
import com.zjgsu.course.model.ScheduleSlot;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 课程数据访问层
 * 使用内存存储简化实现
 */
@Repository
public class CourseRepository {

    private final Map<String, Course> courses = new ConcurrentHashMap<>();

    public CourseRepository() {
        // 初始化示例数据
        initSampleData();
    }

    private void initSampleData() {
        // 创建示例课程
        Course course1 = new Course(
                "CS101",
                "计算机科学导论",
                new Instructor("T001", "张教授", "zhang@zjgsu.edu.cn"),
                new ScheduleSlot(DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(10, 0), 50),
                60
        );

        Course course2 = new Course(
                "CS202",
                "数据结构与算法",
                new Instructor("T002", "李教授", "li@zjgsu.edu.cn"),
                new ScheduleSlot(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(16, 0), 45),
                50
        );

        Course course3 = new Course(
                "CS303",
                "操作系统原理",
                new Instructor("T003", "王教授", "wang@zjgsu.edu.cn"),
                new ScheduleSlot(DayOfWeek.FRIDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), 40),
                45
        );

        courses.put(course1.getId(), course1);
        courses.put(course2.getId(), course2);
        courses.put(course3.getId(), course3);
    }

    /**
     * 查找所有课程
     */
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    /**
     * 根据ID查找课程
     */
    public Course findById(String id) {
        return courses.get(id);
    }

    /**
     * 根据课程代码查找课程
     */
    public Course findByCode(String code) {
        return courses.values().stream()
                .filter(course -> course.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    /**
     * 保存课程
     */
    public Course save(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    /**
     * 删除课程
     */
    public void delete(String id) {
        courses.remove(id);
    }

    /**
     * 检查课程是否存在
     */
    public boolean exists(String id) {
        return courses.containsKey(id);
    }
}
