#!/bin/bash

# 从消息队列中取出 flink 作业启动的参数，并提交作业


#./ipcmqs -w --pns hy --date 20241102

# 1. 查询flink 集群是否空闲


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
    else
        echo "不匹配预期格式的行: $line"
    fi
done < <(./ipcmqs -r)


echo "DATES: ${DATES}, PNS: ${PNS}, CHANNEL_LIST: ${CHANNEL_LIST}"



TZ='Asia/Shanghai' date +%Y%m%d
TZ='Asia/Tokyo' date +%Y%m%d
TZ='Asia/Kolkata' date +%Y%m%d
