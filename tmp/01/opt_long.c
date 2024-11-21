#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <getopt.h>

#define BUFFER_SIZE 1024

struct option long_options[] = {
    {"help", 0, 0, 'h'},
    {"version", 0, 0, 'v'},
    {"source", 1, 0, 's'},
    {"destination", 1, 0, 'd'},
    {0, 0, 0, 0}
};

int main(int argc, char *argv[]) {
    int opt;
    int long_index;
    char *source_file = NULL;
    char *destination_file = NULL;
    char *optstring = "hvs:d:";
    while ((opt = getopt_long(argc, argv, optstring, long_options, &long_index))!= -1) {
        switch (opt) {
            case 'h':
                printf("Usage: %s -s <source_file> -d <destination_file>\n", argv[0]);
                printf("Options:\n");
                printf(" -h or --help: Display this help message\n");
                printf(" -v or --version: Display version information\n");
                printf(" -s or --source: Specify the source file\n");
                printf(" -d or --destination: Specify the destination file\n");
                return 0;
            case 'v':
                printf("File Processing Program Version 1.0\n");
                return 0;
            case 's':
                source_file = optarg;
                break;
            case 'd':
                destination_file = optarg;
                break;
            case '?':
                printf("Invalid option or missing argument.\n");
                return 1;
        }
    }
    if (source_file == NULL || destination_file == NULL) {
        printf("Source and destination files must be specified.\n");
        return 1;
    }
    // 打开源文件
    int source_fd = open(source_file, O_RDONLY);
    if (source_fd == -1) {
        perror("Error opening source file");
        return 1;
    }
    // 打开目标文件
    int destination_fd = open(destination_file, O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);
    if (destination_fd == -1) {
        perror("Error opening destination file");
        close(source_fd);
        return 1;
    }
    char buffer[BUFFER_SIZE];
    ssize_t bytes_read;
    // 从源文件读取数据并写入目标文件
    while ((bytes_read = read(source_fd, buffer, BUFFER_SIZE)) > 0) {
        ssize_t bytes_written = write(destination_fd, buffer, bytes_read);
        if (bytes_written == -1) {
            perror("Error writing to destination file");
            close(source_fd);
            close(destination_fd);
            return 1;
        }
    }
    if (bytes_read == -1) {
        perror("Error reading from source file");
        close(source_fd);
        close(destination_fd);
        return 1;
    }
    // 关闭文件描述符
    close(source_fd);
    close(destination_fd);
    printf("File processed successfully.\n");
    return 0;
}