#!/bin/bash

# 使用命令替换将程序输出赋值给变量 output
output=$(./a.out)

# 通过换行符 \n 作为分隔符，将 output 分割成数组 lines
IFS=$'\n' read -r -a lines <<< "$output"

echo "output: ${output}"

echo "lines: ${lines}"

# 分别获取并处理每一行数据
date="${lines[0]}"
currencies="${lines[1]}"
channel="${lines[2]}"

echo "日期：$date"
echo "货币对：$currencies"
echo "渠道：$channel"

# 进一步解析，比如提取货币对中的单个货币
IFS=',' read -r currency1 currency2 <<< "$currencies"
echo "第一种货币：$currency1"
echo "第二种货币：$currency2"


