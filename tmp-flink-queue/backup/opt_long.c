#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <getopt.h>

#define BUFFER_SIZE 1024

/**
   name属性
       定义：name是一个字符串，用于指定长选项的名称。
       示例：在struct option long_options[]数组中，如果有一个元素{"help", 0, 0, 'h'}，这里的"help"就是长选项的名称。这意味着在命令行中，用户可以使用--help来表示这个选项。长选项名称通常是具有明确语义的单词，方便用户记忆和使用，比如"version"、"input - file"等，这样用户通过--version或--input - file这样的命令行参数就能很直观地理解选项的用途。
   has_arg属性
       定义：has_arg用于表示长选项是否需要参数以及参数的性质，它是一个整数，有以下几种取值：
       0(no_argument)：表示长选项不需要参数。例如，{"verbose", 0, 0, 'v'}中的verbose选项可能只是一个开关，用于开启或关闭详细输出模式，不需要额外的参数跟在后面。
       1(required_argument)：表示长选项需要一个参数。比如{"input - file", 1, 0, 'i'}，当用户在命令行输入--input - file filename.txt时，filename.txt就是这个长选项input - file的参数，用于指定输入文件的名称。
       2(optional_argument)：表示长选项的参数是可选的。这种情况相对复杂一些，例如{"output - file", 2, 0, 'o'}，用户可以选择提供一个输出文件名称作为参数，如--output - file result.txt，也可以不提供参数，程序可能会使用默认的输出文件名或者有其他的处理方式。
   *flag属性
       定义：*flag是一个指针，通常用于和val属性配合来改变一个变量的值。如果flag为NULL，getopt_long函数返回val的值作为选项的返回值；如果flag不为NULL，当找到该选项时，*flag指向的变量会被设置为val的值。
       示例：假设我们有一个变量int option_enabled = 0;，并且有一个选项{"enable - option", 0, &option_enabled, 1}。当在命令行中发现--enable - option这个选项时，option_enabled的值会被设置为1，用于表示这个选项被启用。
   val属性
       定义：val是一个整数，其作用根据flag属性的值有所不同。
       与flag为NULL的情况配合：如果flag为NULL，val就是getopt_long函数返回的值，表示该选项被识别。例如，{"help", 0, NULL, 'h'}，当getopt_long识别到--help选项时，会返回'h'，在程序中可以根据这个返回值（如在switch语句中）来执行相应的操作，如显示帮助信息。
       与flag不为NULL的情况配合：如果flag不为NULL，val会被用于设置*flag指向的变量的值，如前面flag属性示例中所描述的，用于改变某个变量的状态来记录选项是否被启用。
 */
struct option long_options[] = {
    {"help", 0, 0, 'h'},
    {"version", 0, 0, 'v'},
    {"source", 1, 0, 's'},
    {"destination", 1, 0, 'd'},
    {"other", 1, 0, 'o'},
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
                printf("source_file: %s\n", source_file);
                break;
            case 'd':
                destination_file = optarg;
                break;
            case 'o':
                printf("other value: %s\n", optarg);
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