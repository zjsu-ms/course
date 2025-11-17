package com.zjgsu.course.repository;

import com.zjgsu.course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 学生数据访问层
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    /**
     * 根据学号查找学生
     */
    Optional<Student> findByStudentId(String studentId);

    /**
     * 根据邮箱查找学生
     */
    Optional<Student> findByEmail(String email);

    /**
     * 检查学号是否存在
     */
    boolean existsByStudentId(String studentId);

    /**
     * 检查邮箱是否存在
     */
    boolean existsByEmail(String email);

    /**
     * 按专业查询学生
     */
    List<Student> findByMajor(String major);

    /**
     * 按年级查询学生
     */
    List<Student> findByGrade(Integer grade);
}
