package com.zjgsu.course.service;

import com.zjgsu.course.model.Student;
import com.zjgsu.course.repository.EnrollmentRepository;
import com.zjgsu.course.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 学生业务逻辑层
 */
@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * 创建学生
     */
    public Student createStudent(Student student) {
        // 检查学号是否已存在
        if (studentRepository.existsByStudentId(student.getStudentId())) {
            throw new RuntimeException("Student with studentId " + student.getStudentId() + " already exists");
        }
        return studentRepository.save(student);
    }

    /**
     * 根据ID获取学生
     */
    @Transactional(readOnly = true)
    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    /**
     * 获取所有学生
     */
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * 根据学号获取学生
     */
    @Transactional(readOnly = true)
    public Optional<Student> getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    /**
     * 更新学生
     */
    public Optional<Student> updateStudent(String id, Student student) {
        if (!studentRepository.existsById(id)) {
            return Optional.empty();
        }

        // 检查studentId是否与其他学生重复
        Optional<Student> existingWithSameStudentId = studentRepository.findByStudentId(student.getStudentId());
        if (existingWithSameStudentId.isPresent() && !existingWithSameStudentId.get().getId().equals(id)) {
            throw new RuntimeException("Student with studentId " + student.getStudentId() + " already exists");
        }

        student.setId(id);
        return Optional.of(studentRepository.save(student));
    }

    /**
     * 删除学生
     */
    public boolean deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }

        // 检查学生是否有活跃的选课记录
        if (!enrollmentRepository.findByStudentId(id).isEmpty()) {
            throw new RuntimeException("无法删除：该学生存在选课记录");
        }

        studentRepository.deleteById(id);
        return true;
    }
}
