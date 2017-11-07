#include <stdio.h>

/*
 ============================================================================
 
 Author      : Ztiany
 Description : 通过指针引用数组，用数组名作为函数的参数

 ============================================================================
 */

void arrayPointerSample1();
void arrayPointerSample2();
void arrayPointerSample3();
void arrayPointerSample4();
void arrayPointerSample5(int []);
void multidimensionalArray();

static void invArr(int *pInt, int i) {
    int half = i / 2;
    int end = i - 1;
    int temp;
    for (int j = 0; j < half; ++j, end--) {
        printf("%d\n", j);
        temp = *(pInt + j);
        *(pInt + j) = *(pInt + end);
        *(pInt + end) = temp;
    }
    for (int k = 0; k < i; ++k) {
        printf("%d ,", *(pInt + k));
    }
}

int main() {
    arrayPointerSample1();
    arrayPointerSample2();
    arrayPointerSample3();
    arrayPointerSample4();

    int arr5[] = {1, 2, 3, 4, 5};
    arrayPointerSample5(arr5);

    int arr6[] = {1, 2, 3, 4, 5, 8, 111};
    invArr(arr6, 7);

    multidimensionalArray();
}


//通过指针引用多维数组
void multidimensionalArray() {
    //arr指向arr[0]，即二维数组的0行首地址，arr可以理解为指向指针的指针
    int arr[3][3] = {{1, 2, 3},
                     {5, 6, 7},
                     {8, 9, 10}};

    printf("%d \n", sizeof(arr));//32
    printf("%d \n", sizeof(*arr));//12
    int *arr0 = *arr;//0行0列元素地址
    int *arr1 = arr[0];

    int *arr2 = *(arr + 1);//第2个数组的首地址
    printf("%d\n", *arr2 + 1);//6
    printf("%d", *(*(arr + 2)));//8
}


void arrayPointerSample5(int param[]) {
    //形参数组不是固定地址的，可以被再赋值
    param = param + 3;
    printf("%p", param);
}

void arrayPointerSample4() {
    //通过指针引用数组
    int arr[10];
    for (int j = 0; j < 10; ++j) {
        scanf("%d", arr + j);
    }
    for (int i = 0; i < 10; ++i) {
        printf("%d , ", *(arr + i));//指针法引用数组
    }
    printf("\n");
}

void arrayPointerSample3() {
    //通过指针引用数组
    int arr[10];
    for (int j = 0; j < 10; ++j) {
        scanf("%d", &arr[j]);
    }
    for (int i = 0; i < 10; ++i) {
        printf("%d , ", arr[i]);//下标法引用数组
    }
    printf("\n");

    for (int i = 0; i < 10; ++i) {
        printf("%d , ", *(arr + i));//指针法引用数组
    }
    printf("\n");
}

//在一定情况下，允许对指针进行加减运算
void arrayPointerSample2() {
    int arrA[] = {1, 2, 3, 4, 5};//数组名不代表整个数组，只代表数组元素的首地址
    int *p1 = arrA;
    int *p2 = &arrA[1];
    int *p3 = &arrA[2];

    printf("p1+1 = %d\n", *(p1 + 1));//指针+1指向下一个数组元素
    printf("p2-1 = %d\n", *(p2 - 1));//指针-1指向上一个数组元素
    printf("p2-- = %d\n", --*p2);//指针-1指向上一个数组元素
    printf("p2 = %d\n", *p2);
    printf("p3 - p1 = %lu\n", p3 - p1);//如果指针指向同一个数组的元素，那么它们之间的减法是有意义的，表示p3所指向的元素与p1所指向的元素差(地址差/元素长度)
    //注意：数组是常量，arrA++是无法实现的

}

void arrayPointerSample1() {
    //数组名不代表整个数组，只代表数组元素的首地址
    int arrA[] = {1, 2, 3, 4, 5};
    int *a = arrA;
    int *b = &arrA[0];
    printf("*a = %d ,*b =  %d", *a, *b);
}
