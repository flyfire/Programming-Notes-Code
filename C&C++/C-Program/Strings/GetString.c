/*
 ============================================================================
 
 Author      : Ztiany
 Description : 获取字符串

 ============================================================================
 */

#include <stdlib.h>
#include <stdio.h>

static void scanfSample() {
    char str[100];
    //scanf一次只能读取一个单词
    //输入 ab cd只能获取到ab
    scanf("%s", str);
    puts(str);
}

static void getsSample() {
    char str[100];
    gets(str);
    puts(str);
}

static void fgetsSample(){
    const int SIZE = 20;
    char worlds[SIZE];
    fgets(worlds, SIZE, stdin);
    printf("worlds = %s", worlds);
    fputs(worlds, stdout);
}

int main() {
    fgetsSample();
    //scanfSample();
    //getsSample();
    return EXIT_SUCCESS;
}