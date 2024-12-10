#!/bin/bash

# 从消息队列中取出 flink 作业启动的参数，并提交作业

source /etc/profile

CURRENT_TIME=$(date +"%Y-%m-%d %H:%M:%S")
echo "task runner: ${CURRENT_TIME}"

# 1. 查询flink 集群是否空闲
FLINK_CLUSTER_IDLE=false
while read line; do
  if [[ $line =~ "Flink cluster is idle" ]]; then
    echo "Flink cluster is idle"
    FLINK_CLUSTER_IDLE=true
  fi
done < <(python3 ./flink_cluster_job_info.py)

if [[ ! "${FLINK_CLUSTER_IDLE}x" = "true"x ]]; then
  echo "Flink cluster is busy"
  exit 0
fi

# flink 集群空闲中
DATES=""
PNS=""
CHANNEL_LIST=""
JOB_NAME=""
while read line; do
  if [[ $line =~ ^dates:\ (.*)$ ]]; then
    DATES=$(echo ${BASH_REMATCH[1]})
  elif [[ $line =~ ^pns:\ (.*)$ ]]; then
    PNS=$(echo ${BASH_REMATCH[1]})
  elif [[ $line =~ ^channelList:\ (.*)$ ]]; then
    CHANNEL_LIST=$(echo ${BASH_REMATCH[1]})
  elif [[ $line =~ ^jobName:\ (.*)$ ]]; then
    JOB_NAME=$(echo ${BASH_REMATCH[1]})
  fi
done < <(./ipcmqs -r)

echo "DATES: ${DATES}, PNS: ${PNS}, CHANNEL_LIST: ${CHANNEL_LIST}, JOB_NAME: ${JOB_NAME}"

# 消息队列中没有数据
if [[ "${DATES}"x = ""x || "${PNS}x" == ""x || "${JOB_NAME}"x == ""x]]; then
  echo "ipcs mq is empty."
  exit 0
fi


# 修改为对应目录，以及命令行参数
cd /home/suyunhong/flink/flink-repetition/flink-1.18.0
./bin/flink run -p  8 -d job-jar/cdap-repetition-job-1.8.0.jar --cdap.batch.runtime.form-date=${DATES} --cdap.batch.runtime.pns=${PNS} --cdap.batch.runtime.channel-list=${CHANNEL_LIST}
cd -

case $JOB_NAME in
     "cohort_batch")
         echo "It's cohort_batch."
         ;;
     "realtime_batch")
         echo "It's a realtime_batch."
         ;;
     "repetition_batch")
         echo "It's a repetition_batch."
         ;;
     *)
         echo "UNKNOWN jobName: ${JOB_NAME}."
         ;;
 esac

#TZ='Asia/Shanghai' date +%Y%m%d
#TZ='Asia/Tokyo' date +%Y%m%d
#TZ='Asia/Kolkata' date +%Y%m%d




