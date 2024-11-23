#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>
#include <getopt.h>
#include <string.h>

// 定义消息结构体
struct msgbuf {
    long mtype;
    char mtext[100];
};

void usage() {
    printf("Usage:./ipc_msg_queue [-r|-w]\n");
    printf("Options:\n");
    printf("  -r, --read    Read from the message queue\n");
    printf("  -w, --write   Write to the message queue\n");
    exit(EXIT_FAILURE);
}

int main(int argc, char *argv[]) {
    int opt;
    int operation = 0;  // 0表示未指定操作，1表示读，2表示写
    struct option long_options[] = {
        {"read", no_argument, 0, 'r'},
        {"write", no_argument, 0, 'w'},
        {0, 0, 0, 0}
    };

    while ((opt = getopt_long(argc, argv, "rw", long_options, NULL))!= -1) {
        switch (opt) {
            case 'r':
                operation = 1;
                break;
            case 'w':
                operation = 2;
                break;
            default:
                usage();
        }
    }

    if (operation == 0) {
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

    if (operation == 1) {  // 读操作
        struct msgbuf buffer;
        if (msgrcv(msgid, &buffer, sizeof(buffer.mtext), 0, IPC_NOWAIT) == -1) {
            perror("msgrcv");
            if (errno!= ENOMSG) {
                // 如果errno不是ENOMSG，表示是其他错误，以失败状态退出
                exit(EXIT_FAILURE);
            }
            // 如果errno是ENOMSG，表示消息队列为空，输出提示信息并以成功状态退出
            printf("There is no message in the queue, exiting...\n");
            exit(EXIT_SUCCESS);
        }
        printf("Received message: %s\n", buffer.mtext);
    } else if (operation == 2) {  // 写操作
        struct msgbuf buffer;
        buffer.mtype = 1;
        printf("Enter the message to send: ");
        fgets(buffer.mtext, sizeof(buffer.mtext), stdin);
        if (msgsnd(msgid, &buffer, strlen(buffer.mtext)+1, 0) == -1) {
            perror("msgsnd");
            exit(EXIT_FAILURE);
        }
        printf("Message sent successfully.\n");
    }

    return 0;
}