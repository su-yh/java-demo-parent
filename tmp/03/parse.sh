#!/bin/bash



./ipcmqs -w --pns hy --date 20241102



./ipcmqs -r | while read line; do
    if [[ $line =~ ^dates:\ (.*)$ ]]; then
        echo "匹配到日期行，日期值为: ${BASH_REMATCH[1]}"
    elif [[ $line =~ ^pns:\ (.*)$ ]]; then
        echo "匹配到pns行，pns值为: ${BASH_REMATCH[1]}"
    elif [[ $line =~ ^channelList:\ (.*)$ ]]; then
        echo "匹配到频道列表行，频道列表值为: ${BASH_REMATCH[1]}"
    else
        echo "不匹配预期格式的行: $line"
    fi
done


TZ='Asia/Shanghai' date +%Y%m%d
TZ='Asia/Tokyo' date +%Y%m%d
TZ='Asia/Kolkata' date +%Y%m%d
