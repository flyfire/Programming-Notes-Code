#include "bionic-api.h"
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <android/log.h>
#include <sys/system_properties.h>


void shell(const char *command) {
    //system没有接收子进程输出的通讯通道，命令执行前主进程一个等待
    int result = system(command);
    if (result == -1 || result == 127) {
        LOGI("system(%s) fail", command);
    } else {
        LOGI("system(%s) success", command);
    }
}

void shellPro(const char *command) {
    FILE *stream;
    stream = popen(command, "r");
    //默认情况下popen是完全缓存的，使用fflush刷新
    fflush(stream);
    if (NULL == stream) {
        LOGI("popen(%s) fail", command);
    } else {
        char buf[1024 * 5];
        int status;
        while (NULL != fgets(buf, 1024 * 5, stream)) {
            LOGI("read: %s", buf);
        }
        status = pclose(stream);
        LOGI("pclose result: %d", status);
    }
}

int
getSystemProperty(char *propertyName/*ro.product.model*/,
                  char *out /*MAX SIZE = PROP_VALUE_MAX*/) {
    if (0 == __system_property_get(propertyName, out)) {
        LOGI("getSystemProperties not find  %s", propertyName);
        return 0;
    }
    LOGI("getSystemProperties find value = %s", out);
    return 1;
}

int findSystemProperty(char *propertyName/*ro,product.model*/,
                       char *out /*MAX SIZE = PROP_VALUE_MAX*/) {
    //__system_property_find用于搜索系统属性，返回值在系统声明周期内有效
    const prop_info *result = __system_property_find(propertyName);
    if (NULL == result) {
        LOGI("findSystemProperties not find  %s", propertyName);
        return 0;
    } else {
        //使用__system_property_read读取prop_info中的值,name为可选出参，用于拷贝属性名
        if (0 == __system_property_read(result, NULL, out)) {
            LOGI("findSystemProperties find value = %s", out);
            return 1;
        } else {
            LOGI("findSystemProperties not find  %s", propertyName);
            return 0;
        }
    }
}

/*在Android系统中，app被当作用户看待*/
int getUID() {
    //获取用户ID
    uid_t uid = getuid();
    //获取程序组ID
    gid_t gid = getgid();
    //获取用户名
    const char *login = getlogin();

    LOGI("uid = %d, gid = %d, login = $s", uid, gid, login);

    return uid;
}