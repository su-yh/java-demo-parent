#!/bin/bash

# 直接将消息队列中的数据读出来，丢弃
for ((offset = 0; offset < 90; offset++)); do
  ./ipcmqs -r
done

