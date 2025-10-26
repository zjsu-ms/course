package com.zjgsu.course.controller;

import com.zjgsu.course.model.Enrollment;
import com.zjgsu.course.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选课管理Controller
 * RESTful API接口
 */
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * 学生选课
     * POST /api/enrollments
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> enroll(@RequestBody EnrollRequest request) {
        try {
            Enrollment enrollment = enrollmentService.enroll(request.courseId, request.studentId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 201);
            response.put("message", "Enrolled successfully");
            response.put("data", enrollment);
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
     * 学生退课
     * DELETE /api/enrollments/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> withdraw(@PathVariable String id) {
        try {
            enrollmentService.withdraw(id);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "Withdrawn successfully");
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

    /**
     * 获取所有选课记录
     * GET /api/enrollments
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "Success");
        response.put("data", enrollments);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据课程ID获取选课记录
     * GET /api/enrollments/course/{courseId}
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Map<String, Object>> getEnrollmentsByCourse(@PathVariable String courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "Success");
        response.put("data", enrollments);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据学生ID获取选课记录
     * GET /api/enrollments/student/{studentId}
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getEnrollmentsByStudent(@PathVariable String studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "Success");
        response.put("data", enrollments);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据ID获取选课记录
     * GET /api/enrollments/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEnrollment(@PathVariable String id) {
        try {
            Enrollment enrollment = enrollmentService.getEnrollmentById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "Success");
            response.put("data", enrollment);
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
     * 选课请求DTO
     */
    public static class EnrollRequest {
        public String courseId;
        public String studentId;
    }
}
