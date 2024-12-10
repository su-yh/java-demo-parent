#!/bin/bash

source /etc/profile

DATES=$(TZ='Asia/Shanghai' date -d "-1 day" '+%Y%m%d')


./ipcmqs -w --pns hy --date ${DATES}

