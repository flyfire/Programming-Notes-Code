/*
 ============================================================================
 
 Author      : Ztiany
 Description : �ַ���

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

    printf("c = %o\n", c);//�԰˽�����ʽ��������׵�ַ
    printf("c = %s\n", c);//���ַ�������ʽ����ַ�����
}

static void getStringLength() {
    char charArr[] = "�ҵ��찡";
    size_t arrLength = strlen(charArr);
    printf("�����ǣ�%zd\n", arrLength);
}

static void compareString() {
    char charArr1[] = "abcd";
    char charArr2[] = "ABCD";
    int result = strcmp(charArr1, charArr2);
    printf("�ַ����ȽϽ����%d\n", result);
}

static void stringCopy() {
    char charArr1[] = "abc";
    char charArr2[] = "ABCD";
    strcpy(charArr2, charArr1);
    printf("���ƺ���ַ�����%s\n", charArr2);
}

static void stringToNumber() {
    char strNumber[] = "100";
    int num = atoi(strNumber);
    printf("תΪ���֣�%d\n", num);
}

static void printString() {
    char str[] = "������\n";
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


