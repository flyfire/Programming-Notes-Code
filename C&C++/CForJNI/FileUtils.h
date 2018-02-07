#ifndef CFORJNI_FILEUTILS_H
#define CFORJNI_FILEUTILS_H

#include <jni.h>

void split_file(const char *path, const char *path_pattern, int file_num);

void merge_file(const char *path_pattern, int file_num, const char *merge_path);

#endif
