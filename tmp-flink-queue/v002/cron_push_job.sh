#!/bin/bash

source /etc/profile

DATES=$(TZ='Asia/Shanghai' date -d "-1 day" '+%Y%m%d')

# jobName参数可选值: cohort_batch | realtime_batch | repetition_batch
./ipcmqs -w --pns hy --date ${DATES} --jobName cohort_batch

