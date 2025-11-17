package com.zjgsu.course.repository;

import com.zjgsu.course.model.Enrollment;
import com.zjgsu.course.model.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 选课记录数据访问层
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

    /**
     * 根据课程ID查找所有选课记录
     */
    List<Enrollment> findByCourseId(String courseId);

    /**
     * 根据学生ID查找所有选课记录
     */
    List<Enrollment> findByStudentId(String studentId);

    /**
     * 根据课程ID和状态查找选课记录
     */
    List<Enrollment> findByCourseIdAndStatus(String courseId, EnrollmentStatus status);

    /**
     * 根据学生ID和状态查找选课记录
     */
    List<Enrollment> findByStudentIdAndStatus(String studentId, EnrollmentStatus status);

    /**
     * 检查学生是否已选某门课程
     */
    boolean existsByCourseIdAndStudentId(String courseId, String studentId);

    /**
     * 查找学生在某门课程的选课记录
     */
    Optional<Enrollment> findByCourseIdAndStudentId(String courseId, String studentId);

    /**
     * 统计某门课程的活跃选课人数
     */
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.courseId = :courseId AND e.status = 'ACTIVE'")
    long countActiveByCourseId(String courseId);

    /**
     * 统计某门课程的选课人数（所有状态）
     */
    long countByCourseId(String courseId);
}
