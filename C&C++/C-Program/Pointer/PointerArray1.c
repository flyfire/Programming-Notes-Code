/*
 ============================================================================
 
 Author      : Ztiany
 Description : ָ������Ͷ���ָ��

 ============================================================================
 */


#include <stdio.h>

#define NEWLINE printf("\n")

void pointerArray1();

void pointerArray2();

void arrayPointer();

int main() {
    pointerArray1();
    pointerArray2();
    arrayPointer();
}


//ָ������
void pointerArray1() {
    // int *a[4]��ָ�����飬��ʾһ��һάָ�����飬���������Ԫ�ض���ָ�����͡�
    //int *p[4]����[]�������ȼ���ߣ������������Ǹ���СΪ4�����飬��p[4]��ʣ�µġ�int *����Ϊ����˵������˵���������ÿһ��Ԫ��Ϊָ��һ���������͵�ָ�롣
    //ָ�������������ַ������ݵ����
    char *bookList[4] = {"CoreJava", "EffectiveJava", "Android", "C ++"};
    for (int i = 0; i < 4; ++i) {
        printf("%s\n", bookList[i]);
    }
    //ָ��ָ���ָ�룺�ַ������������ַ����飬��ô�ַ���������Ҳ��һ��ָ�룬�������Ϊ������һ����ָ�빹�ɵ�����
    //�����ַ����������Ǳ�����һ��ָ�룬ͬʱ��ָ���Ҳ��ָ��
    char **p = bookList;
    printf("%s", *(p + 1));
}

void pointerArray2() {
    int a[5] = {1, 2, 4, 5, 6};
    int *pa[5] = {&a[0], &a[1], &a[2], &a[3], &a[4]};
    int **p = pa;
    for (int i = 0; i < 5; ++i) {
        printf("%d ", **p);
        p++;
    }
    NEWLINE;
}

//����ָ��
void arrayPointer() {
    // int (*a)[4]������ָ�룬����ֱ�������ָ���ָ�룬ֻ�����ָ�����Ͳ���int������int[4]���͵�����
    //�������Ǹ�ָ�룬��*q��ʣ�µġ�int [4]����Ϊ����˵������˵��ָ��qָ��һ������Ϊ4�����顣
    //�������������*����������ã�����[]�����±꣩����[]�������ȼ�����ڡ�*�������ȼ���
    int array[4] = {1, 2, 3, 4};
    int (*a)[4] = &array;
    for (int i = 0; i < 4; ++i) {
        printf("array[%d]=%d\n", i, (*a)[i]);
    }
    printf("(*a)[4]�ĵ�ַ = %p\n", a);
    printf("int array[4]��size = %zd \n", sizeof(array));//4*4=16
}