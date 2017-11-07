/*
 ============================================================================
 
 Author      : Ztiany
 Description : ctype.h系列函数

 ============================================================================
 */

//C 标准库的ctype.h头文件提供了一些函数，可用于测试和映射字符。这些函数接受int作为参数，它的值必须是EOF或表示为一个无符号字符。
//如果参数c满足描述的条件，则这些函数返回非零（true）。如果参数c不满足描述的条件，则这些函数返回零。

#include <stdio.h>
#include <ctype.h>//专门用来处理字符的函数

int main() {
    char c = 'c';
    int result = isalpha(c);
    if (result != 0) {
        printf("c isalpha");
    } else {
        printf("c not isalpha");
    }
    return 0;
}