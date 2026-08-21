#!/bin/bash

PROG_NAME=$0 # 启动命令
ACTION=$1   # start | stop | restart
APP_NAME=$2
APP_PORT=$3
ACTIVE_ENV=$4 # 激活对应环境：与配置文件 application-${ACTIVE_ENV}.yaml 关联
TIME_ZONE=$5

APP_START_TIMEOUT=120    # 等待应用启动的时间
HEALTH_CHECK_URL=http://127.0.0.1:${APP_PORT}/prod-api/actuator/health  # 应用健康检查URL
# JAR_NAME=./${APP_NAME}-${APP_VERSION}.jar # jar包的名字
# JAR_NAME="./${APP_NAME}-*.jar" # jar包的名字

find_jar_file() {
    # 查找所有匹配的jar文件
    # local jar_files=("./${APP_NAME}-*.jar")
    local jar_files=($(ls ./${APP_NAME}-*.jar 2>/dev/null))
    
    # 检查是否存在匹配的文件（排除通配符本身）
    if [[ ! -f "${jar_files[0]}" ]]; then
        echo "错误：未找到匹配的jar文件 ./${APP_NAME}-*.jar"
        exit 1
    fi
    
    # 统计匹配到的文件数量
    local jar_count=${#jar_files[@]}
    if [[ $jar_count -ne 1 ]]; then
        echo "错误：找到多个匹配的jar文件（共$jar_count个）："
        for jar in "${jar_files[@]}"; do
            echo "  - $jar"
        done
        exit 1
    fi
    
    # 返回找到的唯一jar文件名
    echo "${jar_files[0]}"
}

JAR_NAME=$(find_jar_file)

# TODO: suyh - 要删除
echo ${JAR_NAME}
# exit 0



usage() {
    echo "Usage: $PROG_NAME {start|stop|restart}"
    exit 2
}

health_check() {
    exptime=0
    echo "checking ${HEALTH_CHECK_URL}"
    while true
    do
        status_code=$( /usr/bin/curl -L -o /dev/null --connect-timeout 5 -s -w %{http_code} ${HEALTH_CHECK_URL} )
        if [ "$?" == "0" ]; then
            if [[ "$status_code" == "200" || "$status_code" == "404" ]]; then
                break
            fi
        fi
        sleep 1
        ((exptime++))

        printf "\rApplication not started, health check result: %d, Wait app to pass health check: %d..." "${status_code}" "$exptime"

        if [ $exptime -gt ${APP_START_TIMEOUT} ]; then
            echo ""
            echo 'app start failed'
            exit 1
        fi
    done
    echo ""
    echo "check ${HEALTH_CHECK_URL} success"
}

start_application() {
    echo "starting java process with jar: ${JAR_NAME}"
    set -x
            # -Duser.timezone=${TIME_ZONE} \
    nohup java \
            -jar "${JAR_NAME}" \
            --server.port=${APP_PORT} --spring.profiles.active=${ACTIVE_ENV} > /dev/null 2>&1 &
    set +x
    echo "started java process"
}

stop_application() {
    checkjavapid=$( lsof -i:${APP_PORT} | awk '{print $2}' | grep -v PID )

    if [[ ! $checkjavapid ]]; then
        echo -e "\rno java process"
        return
    fi

    echo "stop java process: ${APP_NAME}"
    kill -15 $checkjavapid

    times=60
    for ((e = 1; e <= times; e++)); do
        printf "\r        -- stopping java lasts %d seconds." "$e"
        sleep 1
        checkjavapid=$( lsof -i:${APP_PORT} | awk '{print $2}' | grep -v PID )
        if [[ -n $checkjavapid ]]; then
            # 进程未退出，但是已达到最大等待时间，则强制杀死进程。
            if [[ $e -eq $times ]]; then
                kill -9 $checkjavapid
                echo ""
                echo "Force stopped java process after $times seconds."
                break
            fi
        else
            echo ""
            echo "java process has exited"
            break
        fi
    done
}

start() {
    start_application
    health_check
}

stop() {
    stop_application
}

# if [ -z "$ACTION" ]; then
#     ACTION="restart"
# fi

case "$ACTION" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    *)
        usage
        ;;
esac

