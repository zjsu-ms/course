-- 初始化示例数据
-- 注意：H2数据库会自动执行此脚本

-- 插入示例课程
INSERT INTO courses (id, code, title, instructor_id, instructor_name, instructor_email,
                    schedule_day_of_week, schedule_start_time, schedule_end_time, schedule_expected_attendance,
                    capacity, enrolled, created_at)
VALUES
    ('c001', 'CS101', '计算机科学导论', 'T001', '张教授', 'zhang@zjgsu.edu.cn',
     'MONDAY', '08:00:00', '10:00:00', 50, 60, 0, CURRENT_TIMESTAMP),
    ('c002', 'CS202', '数据结构与算法', 'T002', '李教授', 'li@zjgsu.edu.cn',
     'WEDNESDAY', '14:00:00', '16:00:00', 45, 50, 0, CURRENT_TIMESTAMP),
    ('c003', 'CS303', '操作系统原理', 'T003', '王教授', 'wang@zjgsu.edu.cn',
     'FRIDAY', '10:00:00', '12:00:00', 40, 45, 0, CURRENT_TIMESTAMP);

-- 插入示例学生
INSERT INTO students (id, student_id, name, major, grade, email, created_at)
VALUES
    ('s001', '2024001', '张三', '计算机科学与技术', 2024, 'zhangsan@zjgsu.edu.cn', CURRENT_TIMESTAMP),
    ('s002', '2024002', '李四', '软件工程', 2024, 'lisi@zjgsu.edu.cn', CURRENT_TIMESTAMP),
    ('s003', '2023001', '王五', '计算机科学与技术', 2023, 'wangwu@zjgsu.edu.cn', CURRENT_TIMESTAMP),
    ('s004', '2023002', '赵六', '网络工程', 2023, 'zhaoliu@zjgsu.edu.cn', CURRENT_TIMESTAMP);
