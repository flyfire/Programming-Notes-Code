/*
 ============================================================================
 
 Author      : Ztiany
 Description : 指针数组和多重指针

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


//指针数组
void pointerArray1() {
    // int *a[4]是指针数组，表示一个一维指针数组，数组里面的元素都是指针类型。
    //int *p[4]，“[]”的优先级别高，所以它首先是个大小为4的数组，即p[4]；剩下的“int *”作为补充说明，即说明该数组的每一个元素为指向一个整型类型的指针。
    //指针数组适用于字符串数据的情况
    char *bookList[4] = {"CoreJava", "EffectiveJava", "Android", "C ++"};
    for (int i = 0; i < 4; ++i) {
        printf("%s\n", bookList[i]);
    }
    //指向指针的指针：字符串本来就是字符数组，那么字符串名本身也是一个指针，可以理解为现在有一个由指针构成的数组
    //所以字符串数组名是本身是一个指针，同时它指向的也是指针
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

//数组指针
void arrayPointer() {
    // int (*a)[4]是数组指针，可以直接理解是指针的指针，只是这个指针类型不是int，而是int[4]类型的数组
    //它首先是个指针，即*q，剩下的“int [4]”作为补充说明，即说明指针q指向一个长度为4的数组。
    //两个运算符：“*”（间接引用）、“[]”（下标），“[]”的优先级别大于“*”的优先级别。
    int array[4] = {1, 2, 3, 4};
    int (*a)[4] = &array;
    for (int i = 0; i < 4; ++i) {
        printf("array[%d]=%d\n", i, (*a)[i]);
    }
    printf("(*a)[4]的地址 = %p\n", a);
    printf("int array[4]的size = %zd \n", sizeof(array));//4*4=16
}