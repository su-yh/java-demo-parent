#!/bin/bash

#./ipcmqs -w --pns hy --date 20241102

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
# 从消息队列中取出 flink 作业启动的参数，并提交作业
DATES=""
PNS=""
CHANNEL_LIST=""
while read line; do
  if [[ $line =~ ^dates:\ (.*)$ ]]; then
    DATES=$(echo ${BASH_REMATCH[1]})
  elif [[ $line =~ ^pns:\ (.*)$ ]]; then
    PNS=$(echo ${BASH_REMATCH[1]})
  elif [[ $line =~ ^channelList:\ (.*)$ ]]; then
    CHANNEL_LIST=$(echo ${BASH_REMATCH[1]})
  fi
done < <(./ipcmqs -r)

# 消息队列中没有数据
if [[ "${DATES}"x = ""x ]]; then
  echo "ipcs mq is empty or dates is null"
  exit 0
fi

echo "DATES: ${DATES}, PNS: ${PNS}, CHANNEL_LIST: ${CHANNEL_LIST}"

cd /home/suyunhong/flink/flink-repetition/flink-1.18.0
./bin/flink run -p  8 -d job-jar/cdap-repetition-job-1.8.0.jar
cd -


#TZ='Asia/Shanghai' date +%Y%m%d
#TZ='Asia/Tokyo' date +%Y%m%d
#TZ='Asia/Kolkata' date +%Y%m%d
