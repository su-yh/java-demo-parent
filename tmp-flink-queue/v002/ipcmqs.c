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
    printf("Usage:./ipc_msg_queue [-r|-w] [--dates <date>] [--pns <pns>] [--channelList <channels>] [--jobName <jobName>]\n");
    printf("Options:\n");
    printf("  -r, --read        Read from the message queue\n");
    printf("  -w, --write       Write to the message queue\n");
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

// ./ipcmqs -w --pns hy --date 20241102 --jobName cohort
int main(int argc, char *argv[]) {
    int opt;
    int operation = OTHER_OPERATION;
    char date[20] = "";
    char pns[1024] = "";
    char channelList[2048] = "";
    char jobName[1024] = "";
    struct option long_options[] = {
        {"read", no_argument, 0, 'r'},
        {"write", no_argument, 0, 'w'},
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
        if (msgrcv(msgid, &buffer, sizeof(buffer.mdata), 0, IPC_NOWAIT) == -1) {
            perror("msgrcv");
            if (errno!= ENOMSG) {
                // 如果errno不是ENOMSG，表示是其他错误，以失败状态退出
                exit(EXIT_FAILURE);
            }
            // 如果errno是ENOMSG，表示消息队列为空，输出提示信息并以成功状态退出
            printf("There is no message in the queue, exiting...\n");
            exit(EXIT_SUCCESS);
        }

        parseReceivedMessage(buffer.mdata, date, pns, channelList, jobName);
        printf("dates: %s\n", date);
        printf("pns: %s\n", pns);
        printf("channelList: %s\n", channelList);
        printf("jobName: %s\n", jobName);
    } else if (operation == WRITE_OPERATION) {
        if (strlen(date) == 0 || strlen(pns) == 0 || strlen(jobName) == 0) {
            fprintf(stderr, "Dates, pns jobName cannot be empty when writing to the message queue.\n");
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