package com.zjgsu.course.service;

import com.zjgsu.course.model.Course;
import com.zjgsu.course.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 课程业务逻辑层
 */
@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * 获取所有课程
     */
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * 根据ID获取课程
     */
    @Transactional(readOnly = true)
    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    /**
     * 根据课程代码获取课程
     */
    @Transactional(readOnly = true)
    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Course not found with code: " + code));
    }

    /**
     * 创建课程
     */
    public Course createCourse(Course course) {
        // 检查课程代码是否已存在
        if (courseRepository.existsByCode(course.getCode())) {
            throw new RuntimeException("Course with code " + course.getCode() + " already exists");
        }
        return courseRepository.save(course);
    }

    /**
     * 更新课程
     */
    public Course updateCourse(String id, Course course) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        course.setId(id);
        return courseRepository.save(course);
    }

    /**
     * 删除课程
     */
    public void deleteCourse(String id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    /**
     * 检查课程是否存在
     */
    @Transactional(readOnly = true)
    public boolean courseExists(String id) {
        return courseRepository.existsById(id);
    }
}
