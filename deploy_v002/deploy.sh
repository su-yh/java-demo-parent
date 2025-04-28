#!/bin/bash

PROG_NAME=$0
ACTION=$1   # start | stop | restart
LIB_DIR=$2  # 依赖的第三方jar 包所在的目录
APP_NAME=$3
APP_VERSION=$4
APP_PORT=$5
ACTIVE_ENV=$6 # 激活对应环境：与配置文件 application-${ACTIVE_ENV}.yaml 关联
TIME_ZONE=$7

APP_START_TIMEOUT=120    # 等待应用启动的时间
HEALTH_CHECK_URL=http://127.0.0.1:${APP_PORT}/actuator/health  # 应用健康检查URL
JAR_NAME=./${APP_NAME}-${APP_VERSION}.jar # jar包的名字

# 创建出相关目录
# mkdir -p ${APP_HOME}/logs

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
            echo ""
            echo "code is $status_code"
            if [ "$status_code" == "200" ]; then
                break
            fi
        fi
        sleep 1
        ((exptime++))

        printf "\rApplication not started, Wait app to pass health check: %d..." "$exptime"

        if [ $exptime -gt ${APP_START_TIMEOUT} ]; then
            echo ""
            echo 'app start failed'
            exit 1
        fi
    done
    echo "check ${HEALTH_CHECK_URL} success"
}

start_application() {
    echo "starting java process"
    set -x
    nohup java -Xms128m -Xss256k \
            -Duser.timezone=${TIME_ZONE} \
            -Dloader.path=${LIB_DIR} -cp ${JAR_NAME} org.springframework.boot.loader.PropertiesLauncher \
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

if [ -z "$ACTION" ]; then
    ACTION="restart"
fi

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

