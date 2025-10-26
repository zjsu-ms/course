#!/bin/bash

# 课程管理系统API测试脚本

BASE_URL="http://localhost:8080"

echo "================================"
echo "课程管理系统 API 测试"
echo "================================"
echo ""

# 测试1：获取所有课程
echo "1. 获取所有课程"
echo "GET $BASE_URL/api/courses"
curl -s $BASE_URL/api/courses | jq '.'
echo ""
echo "--------------------------------"
echo ""

# 获取第一个课程的ID（用于后续测试）
COURSE_ID=$(curl -s $BASE_URL/api/courses | jq -r '.data[0].id')
echo "使用课程ID: $COURSE_ID 进行后续测试"
echo ""

# 测试2：获取单个课程
echo "2. 获取单个课程"
echo "GET $BASE_URL/api/courses/$COURSE_ID"
curl -s $BASE_URL/api/courses/$COURSE_ID | jq '.'
echo ""
echo "--------------------------------"
echo ""

# 测试3：学生选课
echo "3. 学生选课"
echo "POST $BASE_URL/api/enrollments"
curl -s -X POST $BASE_URL/api/enrollments \
  -H "Content-Type: application/json" \
  -d "{\"courseId\":\"$COURSE_ID\",\"studentId\":\"S001\"}" | jq '.'
echo ""
echo "--------------------------------"
echo ""

# 测试4：查看学生的选课记录
echo "4. 查看学生S001的选课记录"
echo "GET $BASE_URL/api/enrollments/student/S001"
curl -s $BASE_URL/api/enrollments/student/S001 | jq '.'
echo ""
echo "--------------------------------"
echo ""

# 测试5：查看课程的选课情况
echo "5. 查看课程的选课情况"
echo "GET $BASE_URL/api/enrollments/course/$COURSE_ID"
curl -s $BASE_URL/api/enrollments/course/$COURSE_ID | jq '.'
echo ""
echo "--------------------------------"
echo ""

# 测试6：尝试重复选课（应该失败）
echo "6. 尝试重复选课（预期失败）"
echo "POST $BASE_URL/api/enrollments"
curl -s -X POST $BASE_URL/api/enrollments \
  -H "Content-Type: application/json" \
  -d "{\"courseId\":\"$COURSE_ID\",\"studentId\":\"S001\"}" | jq '.'
echo ""
echo "--------------------------------"
echo ""

# 测试7：创建新课程
echo "7. 创建新课程"
echo "POST $BASE_URL/api/courses"
curl -s -X POST $BASE_URL/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "code": "CS404",
    "title": "软件工程",
    "instructor": {
      "id": "T004",
      "name": "赵教授",
      "email": "zhao@zjgsu.edu.cn"
    },
    "schedule": {
      "dayOfWeek": "TUESDAY",
      "startTime": "14:00",
      "endTime": "16:00",
      "expectedAttendance": 40
    },
    "capacity": 50
  }' | jq '.'
echo ""
echo "--------------------------------"
echo ""

# 测试8：再次获取所有课程（应该看到新课程）
echo "8. 再次获取所有课程"
echo "GET $BASE_URL/api/courses"
curl -s $BASE_URL/api/courses | jq '.'
echo ""
echo "================================"
echo "测试完成！"
echo "================================"
