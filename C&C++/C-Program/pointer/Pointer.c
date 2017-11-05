/*
 ============================================================================

 Author      : Ztiany
 Description : 定义指针与使用指针

 ============================================================================
 */
#include <stdio.h>
#include <malloc.h>

void pointerSample();

void errorSample();

void findMax();

void swapMax();


int main() {

    pointerSample();
    errorSample();
    findMax();
    swapMax();
    return 0;
}

void constPointer() {
    int a = 4;
    const const int *pa = &a;//此时无发修改pa的值也无法修改pa指向的值
    const int *pa1 = &a;//此时无发修改pa的值

}

void swapMax() {
    void swap(int *, int *);
    int a, b, *p1, *p2;
    scanf("%d %d", &a, &b);
    p1 = &a;
    p2 = &b;
    if (a < b) {
        swap(p1, p2);
    }
    printf("较大的数为 %d, 较小的数为 %d\n", a, b);
}

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;

    /*      永远不要这样做，可能会造成意想不到的结果
                        int *tem;//定义一个指针而没有赋值，这时这个指针会指向一个未知的地址
                        *tem = *a;//而如果此时给 *tem赋值，就会改变那个未知地址的值
    */
}


void findMax() {
    int *p1, *p2, a, b;
    scanf("%d %d", &a, &b);
    p1 = &a;
    p2 = &b;
    if (a < b) {
        p1 = &b;
        p2 = &a;
    }
    printf("较大的数为 %d, 较小的数为 %d\n", *p1, *p2);
}

void errorSample() {
    /*
            这里会导致程序异常。因为地址10肯定不存在
            int * a = 10;
            printf("%d", *a);
     */
    printf("int * a = 10; 是错误的，不合法的。\n");
}

void pointerSample() {
    int a = 4;
    int *aP = &a;//取a的地址赋值给指针aP
    printf("指针 aP = %X\n", aP);
    printf("指针 aP 的值 = %d\n", *aP);
    *aP = 7;//取aP的地址，赋值，也就是给a赋值
    printf("变量 a = %d\n", a);
}
