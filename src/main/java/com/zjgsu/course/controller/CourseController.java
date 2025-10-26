package com.zjgsu.course.controller;

import com.zjgsu.course.model.Course;
import com.zjgsu.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程管理Controller
 * RESTful API接口
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 获取所有课程
     * GET /api/courses
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "Success");
        response.put("data", courses);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据ID获取课程
     * GET /api/courses/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCourse(@PathVariable String id) {
        try {
            Course course = courseService.getCourseById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "Success");
            response.put("data", course);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 404);
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * 创建课程
     * POST /api/courses
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createCourse(@Valid @RequestBody Course course) {
        try {
            Course created = courseService.createCourse(course);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 201);
            response.put("message", "Course created successfully");
            response.put("data", created);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 400);
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * 更新课程
     * PUT /api/courses/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCourse(
            @PathVariable String id,
            @Valid @RequestBody Course course) {
        try {
            Course updated = courseService.updateCourse(id, course);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "Course updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 404);
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * 删除课程
     * DELETE /api/courses/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable String id) {
        try {
            courseService.deleteCourse(id);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "Course deleted successfully");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 404);
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
