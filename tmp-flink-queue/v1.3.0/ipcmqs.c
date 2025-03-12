#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>
#include <getopt.h>
#include <string.h>
#include <errno.h>

struct msgbuf {
    long mtype;
    char mdata[4098];
};

#define MSG_TYPE 1
#define OTHER_OPERATION 'o'
#define READ_OPERATION 'r'
#define WRITE_OPERATION 'w'

// 打印使用帮助信息
void usage() {
    printf("Usage:./ipc_msg_queue [-r|-w] [--msgflg <option>] [--dates <date>] [--pns [pns]] [--channelList <channels>] [--jobName <jobName>]\n");
    printf("Options:\n");
    printf("  -r, --read        Read from the message queue\n");
    printf("  -w, --write       Write to the message queue\n");
    printf("  --msgflg <option> Option can be 0 (blocking), IPC_NOWAIT (non-blocking), MSG_NOERROR (truncate if too long)\n");
    printf("  -d, --dates       Specify date\n");
    printf("  -p, --pns         Specify pns\n");
    printf("  -c, --channelList Specify channel list\n");
    printf("  -j, --jobName     Specify job name\n");
    exit(EXIT_FAILURE);
}

// 格式化消息并返回消息长度
int formatMessage(char *buffer, const char *date, const char *pns, const char *channelList, const char *jobName) {
    int offset = 0;
    int dateLen = strlen(date);
    *((int*)(buffer + offset)) = dateLen;
    offset += sizeof(int);
    strcpy(buffer + offset, date);
    offset += dateLen;

    int pnsLen = strlen(pns);
    *((int*)(buffer + offset)) = pnsLen;
    offset += sizeof(int);
    strcpy(buffer + offset, pns);
    offset += pnsLen;

    int channelListLen = strlen(channelList);
    *((int*)(buffer + offset)) = channelListLen;
    offset += sizeof(int);
    strcpy(buffer + offset, channelList);
    offset += channelListLen;

    int jobNameLen = strlen(jobName);
    *((int*)(buffer + offset)) = jobNameLen;
    offset += sizeof(int);
    strcpy(buffer + offset, jobName);
    offset += jobNameLen;

    return offset;
}

// 解析读取到的消息
void parseReceivedMessage(const char *buffer, char *date, char *pns, char *channelList, char *jobName) {
    int offset = 0;
    int dateLen = *((int*)(buffer + offset));
    offset += sizeof(int);
    strncpy(date, buffer + offset, dateLen);
    date[dateLen] = '\0';
    offset += dateLen;

    int pnsLen = *((int*)(buffer + offset));
    offset += sizeof(int);
    strncpy(pns, buffer + offset, pnsLen);
    pns[pnsLen] = '\0';
    offset += pnsLen;

    int channelListLen = *((int*)(buffer + offset));
    offset += sizeof(int);
    strncpy(channelList, buffer + offset, channelListLen);
    channelList[channelListLen] = '\0';
    offset += channelListLen;

    int jobNameLen = *((int*)(buffer + offset));
    offset += sizeof(int);
    strncpy(jobName, buffer + offset, jobNameLen);
    jobName[jobNameLen] = '\0';
    offset += channelListLen;
}

// 使用示例
// 写一条消息到消息队列中
// ./ipcmqs -w --pns hy --date 20241102 --jobName cohort
// 从消息队列读消息，并且当没有数据时，阻塞等待
// ./ipcmqs -r --msgflg 0
// 从消息队列读消息，当没有数据时，直接结束退出
// ./ipcmqs -r --msgflg IPC_NOWAIT 或者(默认就是不阻塞) ./ipcmqs -r
int main(int argc, char *argv[]) {
    int opt;
    int operation = OTHER_OPERATION;
    char date[20] = "";
    char pns[1024] = "";
    char channelList[2048] = "";
    char jobName[1024] = "";
    // 新增一个变量用于存储wait选项对应的参数值
    int msgflg = IPC_NOWAIT;
    struct option long_options[] = {
        {"read", no_argument, 0, 'r'},
        {"write", no_argument, 0, 'w'},
        {"msgflg", required_argument, 0, 0},  // msgflg长选项，有对应参数，val设为0
        {"dates", required_argument, 0, 'd'},
        {"pns", required_argument, 0, 'p'},
        {"channelList", no_argument, 0, 'c'},
        {"jobName", required_argument, 0, 'j'},
        {0, 0, 0, 0}
    };

    while ((opt = getopt_long(argc, argv, "rw", long_options, NULL))!= -1) {
        switch (opt) {
            case 'r':
                operation = READ_OPERATION;
                break;
            case 'w':
                operation = WRITE_OPERATION;
                break;
            case 0:
                if (strcmp(optarg, "msgflg") == 0) {
                    // 根据传入的参数值设置wait_option变量
                    if (strcmp(optarg, "0") == 0) {
                        msgflg = 0;
                    } else if (strcmp(optarg, "IPC_NOWAIT") == 0) {
                        msgflg = IPC_NOWAIT;
                    // MSG_EXCEPT 选项编译通不过，就算了吧，不用此选项就好。
                    // } else if (strcmp(optarg, "MSG_EXCEPT") == 0) {
                    //     msgflg = MSG_EXCEPT;
                    } else if (strcmp(optarg, "MSG_NOERROR") == 0) {
                        msgflg = MSG_NOERROR;
                    } else {
                        fprintf(stderr, "Invalid argument for --msgflg option. value: %s\n", optarg);
                        usage();
                    }
                }
                break;
            case 'd':
                strcpy(date, optarg);
                break;
            case 'p':
                strcpy(pns, optarg);
                break;
            case 'c':
                strcpy(channelList, optarg);
                break;
            case 'j':
                strcpy(jobName, optarg);
                break;
            default:
                usage();
        }
    }

    if (operation == OTHER_OPERATION) {
        usage();
    }

    // 使用ftok生成key，这里使用当前目录和一个字符常量作为参数，可根据实际情况调整
    key_t key = ftok("/tmp", 'a');
    if (key == -1) {
        perror("ftok failed");
        exit(EXIT_FAILURE);
    }

    // 获取消息队列标识符，如果不存在则创建
    int msgid = msgget(key, IPC_CREAT | 0666);
    if (msgid == -1) {
        perror("msgget failed");
        exit(EXIT_FAILURE);
    }

    if (operation == READ_OPERATION) {
        struct msgbuf buffer;
        // 根据wait_option的值来调用msgrcv函数
        int result = msgrcv(msgid, &buffer, sizeof(buffer.mdata), 0, msgflg);
        if (result == -1) {
            if (msgflg == IPC_NOWAIT) {
                if (errno!= ENOMSG) {
                    // 如果errno不是ENOMSG，表示是其他错误，以失败状态退出
                    exit(EXIT_FAILURE);
                }
                // 如果errno是ENOMSG，表示消息队列为空，输出提示信息并以成功状态退出
                printf("There is no message in the queue, exiting...\n");
                exit(EXIT_SUCCESS);
            }
            // 其他错误情况（比如阻塞等待时出错等），以失败状态退出
            exit(EXIT_FAILURE);
        }

        parseReceivedMessage(buffer.mdata, date, pns, channelList, jobName);
        printf("dates: %s\n", date);
        printf("pns: %s\n", pns);
        printf("channelList: %s\n", channelList);
        printf("jobName: %s\n", jobName);
    } else if (operation == WRITE_OPERATION) {
        if (strlen(date) == 0 || strlen(jobName) == 0) {
            fprintf(stderr, "Dates, jobName cannot be empty when writing to the message queue.\n");
            exit(EXIT_FAILURE);
        }
        struct msgbuf buffer;
        buffer.mtype = MSG_TYPE;
        int msgLength = formatMessage(buffer.mdata, date, pns, channelList, jobName);
        if (msgsnd(msgid, &buffer, msgLength, 0) == -1) {
            perror("msgsnd");
            exit(EXIT_FAILURE);
        }
        printf("Message sent successfully.\n");
    }

    return 0;
}