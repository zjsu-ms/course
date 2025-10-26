package com.zjgsu.course.controller;

import com.zjgsu.course.model.Student;
import com.zjgsu.course.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 学生管理Controller
 * RESTful API接口
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 获取所有学生
     * GET /api/students
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "Success");
        response.put("data", students);
        return ResponseEntity.ok(response);
    }

    /**
     * 根据ID获取学生
     * GET /api/students/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStudent(@PathVariable String id) {
        Optional<Student> student = studentService.getStudentById(id);
        Map<String, Object> response = new HashMap<>();

        if (student.isPresent()) {
            response.put("code", 200);
            response.put("message", "Success");
            response.put("data", student.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 404);
            response.put("message", "Student not found with id: " + id);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * 创建学生
     * POST /api/students
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createStudent(@Valid @RequestBody Student student) {
        try {
            Student created = studentService.createStudent(student);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 201);
            response.put("message", "Student created successfully");
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
     * 更新学生
     * PUT /api/students/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStudent(
            @PathVariable String id,
            @Valid @RequestBody Student student) {
        Optional<Student> updated = studentService.updateStudent(id, student);
        Map<String, Object> response = new HashMap<>();

        if (updated.isPresent()) {
            response.put("code", 200);
            response.put("message", "Student updated successfully");
            response.put("data", updated.get());
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 404);
            response.put("message", "Student not found with id: " + id);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * 删除学生
     * DELETE /api/students/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable String id) {
        boolean deleted = studentService.deleteStudent(id);
        Map<String, Object> response = new HashMap<>();

        if (deleted) {
            response.put("code", 200);
            response.put("message", "Student deleted successfully");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } else {
            response.put("code", 404);
            response.put("message", "Student not found with id: " + id);
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
