#!/bin/bash


APP_HOME=/home/suyunhong/lilishop/api # 应用所在路径
LIB_DIR=${APP_HOME}/lib            # 依赖的第三方jar 包所在的目录
APP_VERSION=4.3.0      # 版本号
ACTIVE_ENV=prod        # 激活对应环境：与配置文件 application-${ACTIVE_ENV}.yaml 关联
TIME_ZONE=Asia/Kolkata # 系统运行时区




# 若未传入参数，默认使用 restart
if [ $# -eq 0 ]; then
    action="restart"
else
    action=$1
fi



cd ${APP_HOME}
# buyer-api 监听端口：8888
./deploy.sh ${action} ${LIB_DIR} buyer-api ${APP_VERSION} 8888 ${ACTIVE_ENV} ${TIME_ZONE}
# common-api 监听端口：8890
./deploy.sh ${action} ${LIB_DIR} common-api ${APP_VERSION} 8890 ${ACTIVE_ENV} ${TIME_ZONE}
# consumer 监听端口：8886
./deploy.sh ${action} ${LIB_DIR} consumer ${APP_VERSION} 8886 ${ACTIVE_ENV} ${TIME_ZONE}
# im-api 监听端口：8885
./deploy.sh ${action} ${LIB_DIR} im-api ${APP_VERSION} 8885 ${ACTIVE_ENV} ${TIME_ZONE}
# manager-api 监听端口：8887
./deploy.sh ${action} ${LIB_DIR} manager-api ${APP_VERSION} 8887 ${ACTIVE_ENV} ${TIME_ZONE}
# shller-api 监听端口：8889
./deploy.sh ${action} ${LIB_DIR} seller-api ${APP_VERSION} 8889 ${ACTIVE_ENV} ${TIME_ZONE}
cd -


