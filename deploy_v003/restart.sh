#! /bin/bash

APP_HOME=/home/suyunhong/cem/cem-jobs  # 应用所在路径
LIB_DIR=${APP_HOME}/lib            # 依赖的第三方jar 包所在的目录
ACTIVE_ENV=suyh        # 激活对应环境：与配置文件 application-${ACTIVE_ENV}.yaml 关联
TIME_ZONE=Asia/Shanghai # 系统运行时区
# TIME_ZONE=Asia/Kolkata # 系统运行时区




# 若未传入参数，默认使用 restart
if [ $# -eq 0 ]; then
    action="restart"
else
    action=$1
fi



cd ${APP_HOME}
pwd
./.deploy.sh ${action} cem-scheduling-job 8919 ${ACTIVE_ENV} ${TIME_ZONE}
cd -

