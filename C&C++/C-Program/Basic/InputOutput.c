/*
 ============================================================================
 
 Author      : Ztiany
 Description : 输入输出

 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

//按下Ctrl+Z作为结束信号
static void goodInput(){
    int ch;
    while ((ch = getchar()) != EOF) {
        putchar(ch);
    }
}

int main() {
    goodInput();
    return EXIT_SUCCESS;
}