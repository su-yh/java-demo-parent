

## 版本升级与变化

### v1.0.0



## 部署

1. 将这些文件放到同一个目录

2. 修改脚本`flink_cluster_job_info.py` 中的 `base_url` 的值

   ```python
   # web UI 的IP 和端口  端口如果没有修改，默认值是8081 (rest.port: 8081)
   base_url = "http://192.168.8.56:8102"
   ```

   

3. 修改`cron_submit_flink_job.sh` 脚本中 flink job 提交命令

   ```shell
   # 修改为对应目录，以及命令行参数
   cd /home/suyunhong/flink/flink-repetition/flink-1.18.0
   ./bin/flink run -d job-jar/cdap-repetition-job-1.8.0.jar --cdap.batch.runtime.form-date=${DATES} --cdap.batch.runtime.pns=${PNS} --cdap.batch.runtime.channel-list=${CHANNEL_LIST}
   cd -
   ```

4. 配置定时任务

   ```shell
   # 每分钟都会尝试从消息队列中取参数提交作业
   * * * * * cd /home/suyunhong/flink/msg_queue && ./cron_submit_flink_job.sh
   # 整点推送一次任务，由于时区相关，具体应该哪个时间推送哪个时区的作业，需要自行调整。
   0 2 * * * cd /home/suyunhong/flink/msg_queue && ./cron_push_job.sh
   ```

   



## 脚本与文件说明

- `ipcmqs` 命令

  > 读写消息队列的命令

  ```shell
  Usage:./ipc_msg_queue [-r|-w] [--dates <date>] [--pns <pns>] [--channelList <channels>]
  Options:
    -r, --read        Read from the message queue
    -w, --write       Write to the message queue
    -d, --dates       Specify date
    -p, --pns         Specify pns
    -c, --channelList Specify channel list
  
  ```

  > 写消息到消息队列

  ```shell
  ./ipcmqs -w --pns hy,bt --date 20241124 --channelList subch_1000484,subch_1000501
  ```

  > 读消息队列中的消息数据

  ```shell
  ./ipcmqs -r
  ```

- `cron_push_job.sh` 脚本

  > 利用`ipcmqs` 命令将消息推送到消息队列
  >
  > ==由于是具体的pn list 以及对应时区的配置，该脚本中的相关参数需要自行调整以及修改==

- `flink_cluster_job_info.py` 脚本

  > python  脚本，查询flink 集群是否空闲。

- `cron_submit_flink_job.sh` 脚本

  > 尝试提交作业到flink 集群
  >
  > 当flink 集群空闲时，从消息队列取消息，得到job 运行所需参数。使用这些参数提交 flink 作业















