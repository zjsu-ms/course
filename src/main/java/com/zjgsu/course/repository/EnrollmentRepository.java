package com.zjgsu.course.repository;

import com.zjgsu.course.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 选课记录数据访问层
 * 使用内存存储简化实现
 */
@Repository
public class EnrollmentRepository {

    private final Map<String, Enrollment> enrollments = new ConcurrentHashMap<>();

    /**
     * 查找所有选课记录
     */
    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments.values());
    }

    /**
     * 根据ID查找选课记录
     */
    public Enrollment findById(String id) {
        return enrollments.get(id);
    }

    /**
     * 根据课程ID查找所有选课记录
     */
    public List<Enrollment> findByCourseId(String courseId) {
        return enrollments.values().stream()
                .filter(enrollment -> enrollment.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    /**
     * 根据学生ID查找所有选课记录
     */
    public List<Enrollment> findByStudentId(String studentId) {
        return enrollments.values().stream()
                .filter(enrollment -> enrollment.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    /**
     * 检查学生是否已选某门课程
     */
    public boolean existsByCourseIdAndStudentId(String courseId, String studentId) {
        return enrollments.values().stream()
                .anyMatch(enrollment ->
                        enrollment.getCourseId().equals(courseId) &&
                        enrollment.getStudentId().equals(studentId)
                );
    }

    /**
     * 保存选课记录
     */
    public Enrollment save(Enrollment enrollment) {
        enrollments.put(enrollment.getId(), enrollment);
        return enrollment;
    }

    /**
     * 删除选课记录
     */
    public void delete(String id) {
        enrollments.remove(id);
    }

    /**
     * 统计某门课程的选课人数
     */
    public long countByCourseId(String courseId) {
        return enrollments.values().stream()
                .filter(enrollment -> enrollment.getCourseId().equals(courseId))
                .count();
    }
}
