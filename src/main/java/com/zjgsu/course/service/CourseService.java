package com.zjgsu.course.service;

import com.zjgsu.course.model.Course;
import com.zjgsu.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程业务逻辑层
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * 获取所有课程
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * 根据ID获取课程
     */
    public Course getCourseById(String id) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        return course;
    }

    /**
     * 根据课程代码获取课程
     */
    public Course getCourseByCode(String code) {
        Course course = courseRepository.findByCode(code);
        if (course == null) {
            throw new RuntimeException("Course not found with code: " + code);
        }
        return course;
    }

    /**
     * 创建课程
     */
    public Course createCourse(Course course) {
        // 检查课程代码是否已存在
        if (courseRepository.findByCode(course.getCode()) != null) {
            throw new RuntimeException("Course with code " + course.getCode() + " already exists");
        }
        return courseRepository.save(course);
    }

    /**
     * 更新课程
     */
    public Course updateCourse(String id, Course course) {
        if (!courseRepository.exists(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        course.setId(id);
        return courseRepository.save(course);
    }

    /**
     * 删除课程
     */
    public void deleteCourse(String id) {
        if (!courseRepository.exists(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.delete(id);
    }

    /**
     * 检查课程是否存在
     */
    public boolean courseExists(String id) {
        return courseRepository.exists(id);
    }
}
