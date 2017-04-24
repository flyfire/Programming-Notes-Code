#include <stdio.h>

/*
 ============================================================================
 
 Author      : Ztiany
 Description :   指向函数的指针，函数指针作为函数参数，返回指针的函数

 ============================================================================
 */

void functionPointer();

void functionPointerParameter();

void returnPointerFunction();

/*
 函数指针：
 如果在程序中定义一个函数，在编译时编译系统为函数代码分配一端存储空间，这段存空间袋起始地址(入口地址)称为函数的指针。
 定义函数指针的：类型名(* 指针变量名)(函数参数列表)，例如：int (*p)(int,int);

 */
int main() {
    functionPointer();
    functionPointerParameter();
    returnPointerFunction();
}

//pointer是指向一个一维数组的指针
float *search(float(*pointer)[4], int i) {
    float *pt;
    pt = *(pointer + i);
    return pt;//返回指针的函数
}

void returnPointerFunction() {
    float score[][4] = {{66,  77,  88,  99},
                        {666, 727, 868, 994},
                        {666, 757, 848, 939},
                        {166, 277, 188, 199}};
    float (*Ps)[4] = score;//有一个指针Ps，指向一个一维数组
    float *p;
    int i, k;
    scanf("%d %d", &i, &k);
    p = search(score, k);
    printf("%f", *(p + i));
}

//指针函数作为参数
int runFunction(int(*function)(int, int), int a, int b) {
    return (*function)(a, b);
}

void functionPointerParameter() {
    int maxValue(int, int);
    int (*p)(int, int) = maxValue;
    int a, b;
    scanf("%d %d", &a, &b);
    int result = runFunction(p, a, b);
    printf("%d ", result);
}


void functionPointer() {

    //函数指针，把函数理解为数据类型，所以变量名为maxValue，类型为int
    int maxValue(int, int);

    int a, b;
    scanf("%d %d", &a, &b);

    int (*p)(int, int);

    p = maxValue;
    int c = (*p)(a, b);
    printf("maxValue = %d", c);
}

int maxValue(int a, int b) {
    return a > b ? a : b;
}

