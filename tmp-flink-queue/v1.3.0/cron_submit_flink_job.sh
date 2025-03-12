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
if [[ "${DATES}"x = ""x || "${JOB_NAME}"x == ""x ]]; then
  echo "ipcs mq is empty."
  exit 0
fi


# 修改为对应目录，以及命令行参数
cd /home/suyunhong/flink/flink-merge/flink-1.18.0

case $JOB_NAME in
     "cohort_batch")
         ./bin/flink run -d job-jar/flink-cohort-job-3.1.0.jar --cds.flink.batch.date=${DATES} --cds.flink.batch.pns=${PNS} --cds.flink.batch.channel-list=${CHANNEL_LIST}
         ;;
     "realtime_batch")
         ./bin/flink run -d job-jar/realtime-trend-job-3.1.0.jar --realtime.trend.batch.runtime.dates=${DATES} --realtime.trend.batch.runtime.pns=${PNS} --realtime.trend.batch.runtime.channel-list=${CHANNEL_LIST}
         ;;
     "repetition_batch")
         ./bin/flink run -d job-jar/cdap-repetition-job-3.1.0.jar --cdap.batch.runtime.form-date=${DATES} --cdap.batch.runtime.pns=${PNS} --cdap.batch.runtime.channel-list=${CHANNEL_LIST}
         ;;
     "data_wash")
         ./bin/flink run -d job-jar/flink-data-wash-job-3.1.3.jar --data.wash.flink.batch.date=${DATES} --data.wash.flink.batch.pns=${PNS}
         ;;
     *)
         echo "UNKNOWN jobName: ${JOB_NAME}."
         ;;
esac
cd -


#TZ='Asia/Shanghai' date +%Y%m%d
#TZ='Asia/Tokyo' date +%Y%m%d
#TZ='Asia/Kolkata' date +%Y%m%d




