/*
 ============================================================================

 Author      : Ztiany
 Description : Windows获取路径

 ============================================================================
 */

#include <direct.h>
#include <stdio.h>

int main() {
    char *path[40];
    _getcwd(path, sizeof(path));
    printf("当前路径为：%s", path);
}