package com.zjgsu.course.repository;

import com.zjgsu.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 课程数据访问层
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    /**
     * 根据课程代码查找课程
     */
    Optional<Course> findByCode(String code);

    /**
     * 根据授课教师ID查询课程
     */
    @Query("SELECT c FROM Course c WHERE c.instructor.id = :instructorId")
    List<Course> findByInstructorId(String instructorId);

    /**
     * 查找有剩余容量的课程
     */
    @Query("SELECT c FROM Course c WHERE c.enrolled < c.capacity")
    List<Course> findAvailableCourses();

    /**
     * 按标题关键字模糊查询
     */
    List<Course> findByTitleContaining(String keyword);

    /**
     * 检查课程代码是否存在
     */
    boolean existsByCode(String code);
}
