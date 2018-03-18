#include <stdlib.h>
#include <stdio.h>
#include <android/log.h>

#define LOGE(format, ...) __android_log_print(ANDROID_LOG_ERROR, "NATIVE",  format, ##__VA_ARGS__)
#define LOGI(format, ...) __android_log_print(ANDROID_LOG_INFO, "NATIVE",  format, ##__VA_ARGS__)

void shell(const char *command) {
    //system没有接收子进程输出的通讯通道，命令执行前主进程一个等待
    int result = system(command);
    if (result == 0 || result == 127) {
        LOGE("system(%s) fail", command);
    }
}

void shellPro(const char *command) {
    FILE *stream;
    stream = popen(command, "r");
    //默认情况下popen是完全缓存的，使用fflush刷新
    fflush(stream);
    if (NULL == stream) {
        LOGE("popen(%s) fail", command);
    } else {
        char buf[1024 * 5];
        int status;
        while (NULL != fgets(buf, 1024 * 5, stream)) {
            LOGE("read: %s", buf);
        }
        status = pclose(stream);
        LOGI("pclose result: %d", status);
    }
}

