/*
 ============================================================================

 Author      : Ztiany
 Description : Windows��ȡ·��

 ============================================================================
 */

#include <direct.h>
#include <stdio.h>

int main() {
    char *path[40];
    _getcwd(path, sizeof(path));
    printf("��ǰ·��Ϊ��%s", path);
}