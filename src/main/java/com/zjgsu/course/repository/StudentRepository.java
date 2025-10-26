package com.zjgsu.course.repository;

import com.zjgsu.course.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 学生数据访问层
 * 使用内存存储简化实现
 */
@Repository
public class StudentRepository {

    private final Map<String, Student> students = new ConcurrentHashMap<>();

    public StudentRepository() {
        // 初始化示例数据
        initSampleData();
    }

    private void initSampleData() {
        // 创建示例学生
        Student student1 = new Student(
                "2024001",
                "张三",
                "计算机科学与技术",
                2024,
                "zhangsan@zjgsu.edu.cn"
        );

        Student student2 = new Student(
                "2024002",
                "李四",
                "软件工程",
                2024,
                "lisi@zjgsu.edu.cn"
        );

        Student student3 = new Student(
                "2023001",
                "王五",
                "计算机科学与技术",
                2023,
                "wangwu@zjgsu.edu.cn"
        );

        Student student4 = new Student(
                "2023002",
                "赵六",
                "网络工程",
                2023,
                "zhaoliu@zjgsu.edu.cn"
        );

        students.put(student1.getId(), student1);
        students.put(student2.getId(), student2);
        students.put(student3.getId(), student3);
        students.put(student4.getId(), student4);
    }

    /**
     * 查找所有学生
     */
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    /**
     * 根据ID查找学生
     */
    public Student findById(String id) {
        return students.get(id);
    }

    /**
     * 根据学号查找学生
     */
    public Student findByStudentId(String studentId) {
        return students.values().stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    /**
     * 保存学生
     */
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    /**
     * 删除学生
     */
    public void delete(String id) {
        students.remove(id);
    }

    /**
     * 检查学生是否存在
     */
    public boolean exists(String id) {
        return students.containsKey(id);
    }
}
