/*
 ============================================================================
 
 Author      : Ztiany
 Description : �������

 ============================================================================
 */


#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define RETURN_CODE 0

int main(void) {//������
    int intArr[5];
    intArr[0] = 4;
    int length = 5;
    //���˵�һ��Ԫ�ع̶���ӡΪ0�⣬����Ԫ�ص�ֵ���ǲ�ȷ���ģ���Ϊ���Զ�����
    for (; length >= 0; length--) {
        printf("�����%d��Ԫ�ص�ֵ��%d\n", length, intArr[length]);
    }
    return RETURN_CODE;
}

