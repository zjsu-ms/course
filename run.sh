#!/bin/bash
# run.sh - 构建并启动课程管理系统（单体应用）

set -e

echo "========================================"
echo "课程管理系统启动脚本"
echo "========================================"

# 编译项目
echo ""
echo ">>> 步骤 1/3: 编译项目..."
mvn clean package -DskipTests > /dev/null 2>&1
echo "✓ 项目编译完成"

# 构建并启动 Docker 容器
echo ""
echo ">>> 步骤 2/3: 构建并启动 Docker 容器..."
docker-compose up -d --build

# 等待服务启动
echo ""
echo ">>> 步骤 3/3: 等待服务启动..."
echo "  等待 MySQL 健康检查（约15秒）..."
sleep 15

# 显示服务状态
echo ""
echo "========================================"
echo "✓ 服务已启动"
echo "========================================"
docker-compose ps

# 显示访问信息
echo ""
echo "服务访问地址："
echo "  - 应用服务: http://localhost:8080/api/courses"
echo "  - MySQL数据库: localhost:3306"
echo ""
echo "查看日志: docker-compose logs -f app"
echo "停止服务: docker-compose down"
echo "删除数据: docker-compose down -v"
