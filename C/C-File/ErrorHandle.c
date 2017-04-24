#include <stdio.h>
#include <mem.h>
#include <errno.h>

/*
 ============================================================================
 
 Author      : Ztiany
 Description : 错误处理

 ============================================================================
 */
/*
C 语言不提供对错误处理的直接支持，
 但是作为一种系统编程语言，它以返回值的形式允许您访问底层数据。
 在发生错误时，大多数的 C 或 UNIX 函数调用返回 1 或 NULL，
 同时会设置一个错误代码 errno，该错误代码是全局变量，表示在函数调用期间发生了错误。
 可以在 <error.h> 头文件中找到各种各样的错误代码。

errno、perror() 和 strerror()

C 语言提供了 perror() 和 strerror() 函数来显示与 errno 相关的文本消息。

perror() 函数显示您传给它的字符串，后跟一个冒号、一个空格和当前 errno 值的文本表示形式。
strerror() 函数，返回一个指针，指针指向当前 errno 值的文本表示形式。
 */

int main() {
    FILE *pf;
    pf = fopen("unexist.txt", "rb");
    if (pf == NULL) {

        fprintf(stderr, "Value of errno: %d\n", errno);//输出错误码,errno是全局的
        perror("Error printed by perror");//标准错误输出，出错结果是Error printed by perror: No such file or directory，这是系统根据全局errno加上的
        fprintf(stderr, "Error opening file: %s\n", strerror(errno));//strerror用于根据错误码获取信息
    } else {
        fclose(pf);
    }
    return 0;
}
