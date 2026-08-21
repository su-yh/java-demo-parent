#!/bin/bash

# 打包分支
# BRANCH=v3.4.7
BRANCH=main-suyh
# BRANCH=main

# 远程服务器信息
HOST="192.168.8.114"
USER="builder"
# PASSWORD="builder"
JAR_PREV="cem-scheduling-job"

rm -f ${JAR_PREV}-*.jar

# 远程服务器的目录地址
BUILD_DIR=/home/builder/cem/cem-jobs
# 定义要执行的命令列表
COMMANDS=(
    "echo '=== 开始远程执行 ==='"
    "cd ${BUILD_DIR}"
    "source /etc/profile"
    # "./.queue_rebuild.sh ${BRANCH}"
    "./.queue_rebuild.sh ${BRANCH} include_config"
    "echo '=== 远程执行完成 ==='"
)

# 构建命令字符串
COMMAND_STRING=""
for cmd in "${COMMANDS[@]}"; do
    COMMAND_STRING+="$cmd && "
done
# 移除最后的" && "
COMMAND_STRING=${COMMAND_STRING% && }

# 执行SSH命令（使用sshpass，需提前安装）
ssh "$USER@$HOST" "$COMMAND_STRING"

# 执行远程拷贝
scp ${USER}@${HOST}:${BUILD_DIR}/jars/${JAR_PREV}-*.jar ./



