/*
 ============================================================================
 
 Author      : Ztiany
 Description : 字符串

 ============================================================================
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

static void defineArray() {
    char c[] = {'a', 'b', '\0'};
    char *str1 = "hello";
    char *str2 = "abc""ddd""zz";
    char *str3 = "abc""ddd"
            "zz";

    printf("c = %o\n", c);//以八进制形式输出数组首地址
    printf("c = %s\n", c);//以字符串的形式输出字符数组
}

static void getStringLength() {
    char charArr[] = "我的天啊";
    size_t arrLength = strlen(charArr);
    printf("长度是：%zd\n", arrLength);
}

static void compareString() {
    char charArr1[] = "abcd";
    char charArr2[] = "ABCD";
    int result = strcmp(charArr1, charArr2);
    printf("字符串比较结果：%d\n", result);
}

static void stringCopy() {
    char charArr1[] = "abc";
    char charArr2[] = "ABCD";
    strcpy(charArr2, charArr1);
    printf("复制后的字符串：%s\n", charArr2);
}

static void stringToNumber() {
    char strNumber[] = "100";
    int num = atoi(strNumber);
    printf("转为数字：%d\n", num);
}

static void printString() {
    char str[] = "哈哈哈\n";
    puts(str);
}

static void getString() {
    char str[10];
    gets(str);
    puts(str);
}

int main() {
    defineArray();
    getStringLength();
    compareString();
    stringCopy();
    stringToNumber();
    printString();
    getString();
    return 0;
}


