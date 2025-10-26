package com.zjgsu.course.service;

import com.zjgsu.course.model.Course;
import com.zjgsu.course.model.Enrollment;
import com.zjgsu.course.repository.CourseRepository;
import com.zjgsu.course.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选课业务逻辑层
 */
@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, StudentService studentService) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    /**
     * 学生选课
     */
    public Enrollment enroll(String courseId, String studentId) {
        // 检查学生是否存在
        if (!studentService.getStudentById(studentId).isPresent()) {
            throw new IllegalArgumentException("Student not found with id: " + studentId);
        }

        // 检查课程是否存在
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new RuntimeException("Course not found with id: " + courseId);
        }

        // 检查是否已选过该课程
        if (enrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId)) {
            throw new RuntimeException("Student " + studentId + " has already enrolled in course " + courseId);
        }

        // 检查课程是否已满
        if (course.isFull()) {
            throw new RuntimeException("Course " + courseId + " is full");
        }

        // 创建选课记录
        Enrollment enrollment = new Enrollment(courseId, studentId);
        enrollmentRepository.save(enrollment);

        // 更新课程的选课人数
        course.incrementEnrolled();
        courseRepository.save(course);

        return enrollment;
    }

    /**
     * 学生退课
     */
    public void withdraw(String enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment == null) {
            throw new RuntimeException("Enrollment not found with id: " + enrollmentId);
        }

        // 减少课程的选课人数
        Course course = courseRepository.findById(enrollment.getCourseId());
        if (course != null) {
            course.setEnrolled(course.getEnrolled() - 1);
            courseRepository.save(course);
        }

        enrollmentRepository.delete(enrollmentId);
    }

    /**
     * 获取所有选课记录
     */
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    /**
     * 根据课程ID获取选课记录
     */
    public List<Enrollment> getEnrollmentsByCourse(String courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    /**
     * 根据学生ID获取选课记录
     */
    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    /**
     * 根据ID获取选课记录
     */
    public Enrollment getEnrollmentById(String id) {
        Enrollment enrollment = enrollmentRepository.findById(id);
        if (enrollment == null) {
            throw new RuntimeException("Enrollment not found with id: " + id);
        }
        return enrollment;
    }
}
