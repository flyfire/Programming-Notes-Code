/*
 ============================================================================
 
 Author      : Ztiany
 Description : �ַ���

 ============================================================================
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

//�����ַ���
static void defineArray() {
    char c[] = {'a', 'b', '\0'};
    char *str1 = "hello";
    char *str2 = "abc""ddd""zz";
    char *str3 = "abc""ddd"
            "zz";

    printf("c = %o\n", c);//�԰˽�����ʽ��������׵�ַ
    printf("c = %s\n", c);//���ַ�������ʽ����ַ�����
}

//��ȡ�ַ�������
static void getStringLength() {
    char charArr[] = "�ҵ��찡";
    size_t arrLength = strlen(charArr);
    printf("�����ǣ�%zd\n", arrLength);
}

//�ַ����Ƚ�
static void compareString() {
    char charArr1[] = "abcd";
    char charArr2[] = "ABCD";
    int result = strcmp(charArr1, charArr2);
    printf("�ַ����ȽϽ����%d\n", result);
}

//�ַ�������
static void stringCopy() {
    char charArr1[] = "abc";
    char charArr2[] = "ABCD";
    strcpy(charArr2, charArr1);
    printf("���ƺ���ַ�����%s\n", charArr2);
}

//ת��Ϊ����
static void stringToNumber() {
    char strNumber[] = "100";
    int num = atoi(strNumber);
    printf("תΪ���֣�%d\n", num);
}


//�����ַ���
static void traverseString() {
    char *str = "Hello String";
    //*str��*str!='\0'��ʵ��һ������˼����*str=='\0'ʱ��������ֵ����0��Ҳ����false��
    while (*str) {//*str != '\0'
        putchar(*str);
        str++;
    }
}

int main() {
    //defineArray();
    //getStringLength();
    //compareString();
    //stringCopy();
    //stringToNumber();
    traverseString();
    return 0;
}


