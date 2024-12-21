#!/bin/bash


DATES_BEGIN=2024-11-01
for ((offset = 0; offset < 42; offset++)); do
  DATES=$(date -d "${DATES_BEGIN} +$offset days" +%Y%m%d)
  echo "DATES: ${DATES}"
  ./ipcmqs -w --pns inr,cny --date ${DATES} --jobName cohort_batch
  ./ipcmqs -w --pns inr,cny --date ${DATES} --jobName realtime_batch
  ./ipcmqs -w --pns inr,cny --date ${DATES} --jobName repetition_batch
done

