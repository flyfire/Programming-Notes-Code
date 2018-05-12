/*
 ============================================================================
 
 Author      : Ztiany
 Description : ָ������Ͷ���ָ��

 ============================================================================
 */


#include <stdio.h>

#define NEWLINE printf("\n")

//ָ������
void pointerArray1() {
    //bookList��һ�����飬���������Ԫ�ض����ַ�ָ��(�ַ���)
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
    //����һ����������
    int a[5] = {1, 2, 4, 5, 6};
    //����һ������ָ������
    int *pa[5] = {&a[0], &a[1], &a[2], &a[3], &a[4]};
    //����һ��ָ�룬ָ��pa��p��ָ��ָ���ָ��
    int **p = pa;
    for (int i = 0; i < 5; ++i) {
        printf("%d ", **p);
        p++;//�ƶ�����һ��ָ��ĵ�λ��ָ��ļӼ�������ָ��ָ�������Ϊ��λ��
    }
    NEWLINE;
}


void pointerArray3() {
    //һ���ַ�ָ������
    char *arr[20];
    //arr��������һ��ָ�룬������ָ���Ҳ��һ���ַ�ָ��
    char **parr = arr;
}

//����ָ��1
void arrayPointer1() {
    int array[4] = {1, 2, 3, 4};
    //����int (*a)[4]��һ������ָ�룬��ָ��ָ�����һ������
    int (*a)[4] = &array;
    for (int i = 0; i < 4; ++i) {
        printf("array[%d]=%d\n", i, (*a)[i]);
    }
    printf("(*a)[4]�ĵ�ַ = %p\n", a);
    printf("int array[4]��size = %zd \n", sizeof(array));//4*4=16
}

//����ָ��2
void arrayPointer2() {
    int array[][4] = {{1, 2, 3, 4},
                      {5, 6, 7, 8}};
    int (*a)[4] = array;
    a++;//�ƶ��ĵ�λ�� sizeof(int [4])
    for (int i = 0; i < 4; ++i) {
        printf("array[%d]=%d\n", i, (*a)[i]);
    }
}

int main() {
    pointerArray1();
    pointerArray2();
    pointerArray3();
    arrayPointer1();
    arrayPointer2();
    return 0;
}