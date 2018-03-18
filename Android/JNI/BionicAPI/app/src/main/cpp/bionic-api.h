//
// Created by Administrator on 18.3.18.
//

#ifndef BIONICAPI_BIONIC_API_H
#define BIONICAPI_BIONIC_API_H

void shell(const char *command);

void shellPro(const char *command);


#define LOGE(format, ...) __android_log_print(ANDROID_LOG_ERROR, "NATIVE",  format, ##__VA_ARGS__)
#define LOGI(format, ...) __android_log_print(ANDROID_LOG_INFO, "NATIVE",  format, ##__VA_ARGS__)

#endif //BIONICAPI_BIONIC_API_H
