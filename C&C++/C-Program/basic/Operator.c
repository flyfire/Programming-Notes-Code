/*
 ============================================================================
 Author      : Ztiany
 Description : C���Ե������
 ============================================================================
 */

void bitOperation();//λ����
void printInt(char name, int x);

#include <stdio.h>
#include <iso646.h>//��������޷����������������������ĺ궨�����

int main(void) {
    //λ����
    bitOperation();
    return 1;
}

void bitOperation() {

    int x = 10;
    int y = 12;
    int z = x & y;
    int v = ~x;
    int b = x ^y;
    int n = x | y;
    int m = x << 3;
    int l = x >> 3;

    printInt('x', x);
    printInt('y', y);
    printInt('z', z);
    printInt('v', v);
    printInt('b', b);
    printInt('n', n);
    printInt('m', m);
    printInt('l', l);
}

void printInt(char name, int x) {
    printf("����%c��ֵΪ%d \n", name, x);
}


