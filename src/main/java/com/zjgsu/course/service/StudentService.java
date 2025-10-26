package com.zjgsu.course.service;

import com.zjgsu.course.model.Student;
import com.zjgsu.course.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 学生业务逻辑层
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * 创建学生
     */
    public Student createStudent(Student student) {
        // 检查学号是否已存在
        if (studentRepository.findByStudentId(student.getStudentId()) != null) {
            throw new RuntimeException("Student with studentId " + student.getStudentId() + " already exists");
        }
        return studentRepository.save(student);
    }

    /**
     * 根据ID获取学生
     */
    public Optional<Student> getStudentById(String id) {
        return Optional.ofNullable(studentRepository.findById(id));
    }

    /**
     * 获取所有学生
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * 根据学号获取学生
     */
    public Optional<Student> getStudentByStudentId(String studentId) {
        return Optional.ofNullable(studentRepository.findByStudentId(studentId));
    }

    /**
     * 更新学生
     */
    public Optional<Student> updateStudent(String id, Student student) {
        if (!studentRepository.exists(id)) {
            return Optional.empty();
        }
        student.setId(id);
        return Optional.of(studentRepository.save(student));
    }

    /**
     * 删除学生
     */
    public boolean deleteStudent(String id) {
        if (!studentRepository.exists(id)) {
            return false;
        }
        studentRepository.delete(id);
        return true;
    }
}
