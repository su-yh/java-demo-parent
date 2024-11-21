#!/bin/bash


./a.out | while read line; do
    echo $line
done


TZ='Asia/Shanghai' date +%Y%m%d
TZ='Asia/Tokyo' date +%Y%m%d
TZ='Asia/Kolkata' date +%Y%m%d
