# 课程管理系统 - 单体应用版本

一个简洁的Spring Boot单体应用，演示课程管理系统的基本功能。本项目是[CourseHub微服务项目](../coursehub/)的单体版本，用于教学目的，帮助理解单体应用与微服务架构的差异。

## 📋 目录

- [项目说明](#项目说明)
- [技术栈](#技术栈)
- [项目结构](#项目结构)
- [环境准备](#环境准备)
- [快速开始](#快速开始)
- [详细运行步骤](#详细运行步骤)
- [API文档](#api文档)
- [测试API](#测试api)
- [单体 vs 微服务对比](#单体-vs-微服务对比)
- [学习要点](#学习要点)
- [常见问题](#常见问题)

---

## 项目说明

本项目实现了一个简单的课程管理系统，包含以下核心功能：

### ✅ 功能特性

- **课程管理**
  - 查看所有课程
  - 查看单个课程详情
  - 创建新课程
  - 更新课程信息
  - 删除课程

- **选课管理**
  - 学生选课
  - 学生退课
  - 查看选课记录
  - 按课程查询选课情况
  - 按学生查询已选课程

### 🎯 教学目标

- 理解单体应用的架构设计
- 掌握Spring Boot开发流程
- 学习RESTful API设计
- 理解MVC分层架构
- 对比单体与微服务的差异

## 技术栈

- **Spring Boot** 3.5.6 - 应用框架
- **Java** 25（或Java 17+） - 编程语言
- **Maven** 3.8+ - 构建工具
- **内存存储** - 数据持久化（简化实现）

### 依赖说明

```xml
<!-- Spring Boot Web - 提供RESTful API支持 -->
spring-boot-starter-web

<!-- Spring Boot Validation - 数据验证 -->
spring-boot-starter-validation

<!-- Spring Boot Test - 测试支持 -->
spring-boot-starter-test
```

## 项目结构

```
projects/course/
├── src/
│   ├── main/
│   │   ├── java/com/zjgsu/course/
│   │   │   ├── CourseApplication.java       # 主应用类
│   │   │   ├── model/                        # 实体类
│   │   │   │   ├── Course.java              # 课程实体
│   │   │   │   ├── Instructor.java          # 教师实体
│   │   │   │   ├── ScheduleSlot.java        # 时间安排实体
│   │   │   │   └── Enrollment.java          # 选课记录实体
│   │   │   ├── repository/                   # 数据访问层
│   │   │   │   ├── CourseRepository.java
│   │   │   │   └── EnrollmentRepository.java
│   │   │   ├── service/                      # 业务逻辑层
│   │   │   │   ├── CourseService.java
│   │   │   │   └── EnrollmentService.java
│   │   │   └── controller/                   # 控制层（API）
│   │   │       ├── CourseController.java
│   │   │       └── EnrollmentController.java
│   │   └── resources/
│   │       └── application.yml               # 应用配置
│   └── test/
├── pom.xml                                    # Maven配置
└── README.md                                  # 本文档
```

### 📁 分层说明

- **Model层** - 定义数据实体和业务对象
- **Repository层** - 数据访问，与数据存储交互
- **Service层** - 业务逻辑处理
- **Controller层** - 处理HTTP请求，提供RESTful API

## 环境准备

### 1. 安装Java JDK

确保已安装Java 17或更高版本：

```bash
# 检查Java版本
java -version
```

如果未安装，请从以下地址下载：
- Oracle JDK: https://www.oracle.com/java/technologies/downloads/
- OpenJDK: https://adoptium.net/

### 2. 安装Maven（可选）

项目自带Maven Wrapper（`mvnw`），无需单独安装Maven。

### 3. 安装IDE（推荐）

推荐使用IntelliJ IDEA：
- 下载地址：https://www.jetbrains.com/idea/download/
- Community版本免费且功能完整

## 快速开始

### 方式一：使用Maven Wrapper（推荐）

```bash
# 1. 进入项目目录
cd projects/course

# 2. 编译项目
./mvnw clean compile

# 3. 运行应用
./mvnw spring-boot:run
```

### 方式二：使用IntelliJ IDEA

1. 用IDEA打开项目
2. 找到`CourseApplication.java`
3. 右键点击 -> Run 'CourseApplication'

### 验证启动

浏览器访问：http://localhost:8080/api/courses

如果看到JSON格式的课程列表，说明启动成功！

---

## 详细运行步骤

### 第一步：下载/克隆项目

```bash
# 如果是从Git仓库克隆
git clone <repository-url>
cd microservices/projects/course
```

### 第二步：检查环境

```bash
# 检查Java版本（需要17或更高）
java -version

# 输出示例：
# openjdk version "25" 2025-09-16
# OpenJDK Runtime Environment (build 25+37)
```

### 第三步：编译项目

```bash
# Linux/Mac
./mvnw clean compile

# Windows
mvnw.cmd clean compile
```

**编译过程说明**：
- `clean` - 清理之前的编译文件
- `compile` - 编译源代码

**预期输出**：
```
[INFO] BUILD SUCCESS
[INFO] Total time: 5.234 s
```

### 第四步：运行应用

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

**启动成功标志**：
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

Started CourseApplication in 2.345 seconds
```

应用现在运行在 **http://localhost:8080**

### 第五步：测试API

#### 5.1 使用浏览器测试

直接在浏览器中访问：
- http://localhost:8080/api/courses - 查看所有课程
- http://localhost:8080/api/enrollments - 查看所有选课记录

#### 5.2 使用curl测试

```bash
# 获取所有课程
curl http://localhost:8080/api/courses

# 获取单个课程（替换{id}为实际课程ID）
curl http://localhost:8080/api/courses/{id}

# 学生选课
curl -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{"courseId":"课程ID","studentId":"S001"}'
```

#### 5.3 使用Postman/Apifox测试

1. 创建新请求
2. 设置方法和URL
3. 添加请求体（POST请求）
4. 点击Send发送请求

详细API文档见下文。

---

## API文档

### 课程管理API

#### 1. 获取所有课程

```http
GET /api/courses
```

**响应示例**：
```json
{
  "code": 200,
  "message": "Success",
  "data": [
    {
      "id": "uuid-1",
      "code": "CS101",
      "title": "计算机科学导论",
      "instructor": {
        "id": "T001",
        "name": "张教授",
        "email": "zhang@zjgsu.edu.cn"
      },
      "schedule": {
        "dayOfWeek": "MONDAY",
        "startTime": "08:00",
        "endTime": "10:00",
        "expectedAttendance": 50
      },
      "capacity": 60,
      "enrolled": 0
    }
  ]
}
```

#### 2. 获取单个课程

```http
GET /api/courses/{id}
```

**路径参数**：
- `id` - 课程ID

**响应示例**：
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "id": "uuid-1",
    "code": "CS101",
    "title": "计算机科学导论",
    ...
  }
}
```

#### 3. 创建课程

```http
POST /api/courses
Content-Type: application/json
```

**请求体**：
```json
{
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
}
```

**响应示例**：
```json
{
  "code": 201,
  "message": "Course created successfully",
  "data": {
    "id": "uuid-new",
    "code": "CS404",
    ...
  }
}
```

#### 4. 更新课程

```http
PUT /api/courses/{id}
Content-Type: application/json
```

**请求体**：与创建课程相同

#### 5. 删除课程

```http
DELETE /api/courses/{id}
```

**响应示例**：
```json
{
  "code": 200,
  "message": "Course deleted successfully",
  "data": null
}
```

### 选课管理API

#### 1. 学生选课

```http
POST /api/enrollments
Content-Type: application/json
```

**请求体**：
```json
{
  "courseId": "uuid-1",
  "studentId": "S001"
}
```

**响应示例**：
```json
{
  "code": 201,
  "message": "Enrolled successfully",
  "data": {
    "id": "enrollment-uuid",
    "courseId": "uuid-1",
    "studentId": "S001",
    "enrolledAt": "2025-10-12T21:30:00"
  }
}
```

#### 2. 学生退课

```http
DELETE /api/enrollments/{id}
```

#### 3. 获取所有选课记录

```http
GET /api/enrollments
```

#### 4. 按课程查询选课记录

```http
GET /api/enrollments/course/{courseId}
```

#### 5. 按学生查询已选课程

```http
GET /api/enrollments/student/{studentId}
```

#### 6. 获取单个选课记录

```http
GET /api/enrollments/{id}
```

---

## 测试API

### 完整测试流程

#### 1. 查看初始课程

```bash
curl http://localhost:8080/api/courses
```

应该看到3门预置课程。

#### 2. 学生选课

```bash
curl -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{"courseId":"<从步骤1获取的课程ID>","studentId":"S001"}'
```

#### 3. 查看选课记录

```bash
# 查看所有选课记录
curl http://localhost:8080/api/enrollments

# 查看学生S001的选课记录
curl http://localhost:8080/api/enrollments/student/S001
```

#### 4. 创建新课程

```bash
curl -X POST http://localhost:8080/api/courses \
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
  }'
```

#### 5. 测试业务逻辑

```bash
# 尝试重复选课（应该失败）
curl -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{"courseId":"<相同课程ID>","studentId":"S001"}'

# 预期响应：
# {
#   "code": 400,
#   "message": "Student S001 has already enrolled in course ..."
# }
```

---

## 单体 vs 微服务对比

本项目是CourseHub微服务项目的单体版本。以下是两者的主要区别：

### 架构对比

| 特性 | 单体应用（本项目） | 微服务（CourseHub） |
|------|-------------------|---------------------|
| **应用数量** | 1个应用 | 多个独立服务 |
| **数据库** | 共享数据存储 | 每个服务独立数据库 |
| **部署** | 整体部署 | 独立部署 |
| **技术栈** | 统一技术栈 | 可使用不同技术栈 |
| **服务通信** | 方法调用 | HTTP/RPC/消息队列 |
| **复杂度** | 简单 | 复杂 |
| **团队协作** | 集中式 | 分布式 |

### 代码对比

#### 单体应用（本项目）

```java
// 直接方法调用
@Service
public class EnrollmentService {
    private final CourseRepository courseRepository; // 直接注入
    
    public Enrollment enroll(String courseId, String studentId) {
        Course course = courseRepository.findById(courseId); // 直接访问
        // 业务逻辑...
    }
}
```

#### 微服务（CourseHub）

```java
// 需要通过HTTP/RPC调用其他服务
@Service
public class EnrollmentService {
    private final CatalogClient catalogClient; // HTTP客户端
    
    public Enrollment enroll(String courseId, String studentId) {
        Course course = catalogClient.getCourse(courseId); // 远程调用
        // 业务逻辑...
    }
}
```

### 何时选择单体？

✅ **适合单体的场景**：
- 项目规模小
- 团队规模小（< 10人）
- 需求相对稳定
- 快速开发和部署
- 学习和教学项目

❌ **不适合单体的场景**：
- 大规模项目
- 需要独立扩展不同模块
- 多团队并行开发
- 需要使用不同技术栈

---

## 学习要点

### 1. Spring Boot基础

- **自动配置** - Spring Boot自动配置了Web服务器、JSON转换等
- **依赖注入** - 使用构造函数注入依赖
- **分层架构** - Controller -> Service -> Repository

### 2. RESTful API设计

- **资源导向** - URL表示资源（/api/courses）
- **HTTP方法** - GET（查询）、POST（创建）、PUT（更新）、DELETE（删除）
- **状态码** - 200（成功）、201（创建）、404（未找到）、400（错误请求）
- **统一响应** - 所有API返回统一的JSON格式

### 3. MVC分层

```
Controller（控制层）
    ↓ 调用
Service（业务层）
    ↓ 调用
Repository（数据层）
    ↓ 访问
数据存储（内存/数据库）
```

### 4. 业务逻辑

- **参数验证** - 检查课程是否存在、容量是否已满等
- **异常处理** - 统一的错误响应格式
- **事务性** - 选课时同时更新选课记录和课程人数

---

## 常见问题

### 1. 端口被占用

**错误信息**：
```
Web server failed to start. Port 8080 was already in use.
```

**解决方法**：
- 修改`application.yml`中的端口号：
  ```yaml
  server:
    port: 8081
  ```
- 或停止占用8080端口的程序

### 2. Maven依赖下载慢

**解决方法**：配置阿里云Maven镜像

在`pom.xml`中添加：
```xml
<repositories>
    <repository>
        <id>aliyun</id>
        <url>https://maven.aliyun.com/repository/public</url>
    </repository>
</repositories>
```

### 3. Java版本不兼容

**错误信息**：
```
Unsupported class file major version XX
```

**解决方法**：
- 确保使用Java 17或更高版本
- 或修改`pom.xml`中的Java版本：
  ```xml
  <properties>
      <java.version>17</java.version>
  </properties>
  ```

### 4. IDEA无法识别Spring注解

**解决方法**：
1. 右键项目 -> Maven -> Reload Project
2. File -> Invalidate Caches / Restart

---

## 下一步学习

完成本项目后，可以：

1. **添加数据库**
   - 集成Spring Data JPA
   - 使用H2/MySQL/PostgreSQL

2. **添加认证授权**
   - 使用Spring Security
   - 实现JWT认证

3. **添加测试**
   - 单元测试（JUnit）
   - 集成测试

4. **学习微服务**
   - 查看CourseHub微服务版本
   - 理解服务拆分和通信

5. **添加前端**
   - 使用Vue/React开发前端界面
   - 与后端API集成

---

## 参考资源

- [Spring Boot官方文档](https://spring.io/projects/spring-boot)
- [RESTful API设计指南](https://restfulapi.net/)
- [Maven官方文档](https://maven.apache.org/)

---

## 总结

恭喜你完成了课程管理系统单体应用的学习！通过这个项目，你应该掌握了：

- ✅ Spring Boot项目的基本结构
- ✅ RESTful API的设计和实现
- ✅ MVC分层架构的应用
- ✅ 业务逻辑的处理
- ✅ 单体应用与微服务的差异

这是迈向微服务架构的良好起点。继续学习CourseHub微服务项目，了解如何将单体应用拆分为微服务！
