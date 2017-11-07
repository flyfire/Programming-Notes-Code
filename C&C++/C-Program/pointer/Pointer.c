/*
 ============================================================================

 Author      : Ztiany
 Description : 定义指针与使用指针

 ============================================================================
 */

#include <stdio.h>

static void pointerSample();

static void errorSample();

static void findMax();

static void swapMax();

static void swap(int *, int *);

static void constPointer();

static void pointerSize();

int main() {
    pointerSample();
    pointerSize();
    errorSample();
    findMax();
    swapMax();
    constPointer();
    return 0;
}

static void constPointer() {
    int a = 4;
    //此时无发修改pa的值也无法修改pa指向的值
    const const int *ccpa = &a;
    //此时无发修改pa的值
    const int *cpa = &a;
}

static void swapMax() {
    int a, b, *p1, *p2;
    //https://stackoverflow.com/questions/3420629/what-is-the-difference-between-sscanf-or-atoi-to-convert-a-string-to-an-integer
    scanf("%d %d", &a, &b);
    p1 = &a;
    p2 = &b;
    if (a < b) {
        swap(p1, p2);
    }
    printf("较大的数为 %d, 较小的数为 %d\n", a, b);
}

static void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
    /*
     永远不要这样做，可能会造成意想不到的结果：
       int *tem;//定义一个指针而没有赋值，这时这个指针会指向一个未知的地址
       *tem = *a;//而如果此时给 *tem赋值，就会改变那个未知地址的值
    */
}


static void findMax() {
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

static void errorSample() {
    /*
       这里会导致程序异常。因为地址10肯定不存在
       int * a = 10;
       printf("%d", *a);
     */
    printf("int * a = 10; 是错误的，不合法的。\n");
}

static void pointerSize() {
    int *pi[10];
    char *pc[10];
    float *pf[10];
    double *pd[10];

    struct Student {
        int age;
        char *name;
        char *address;
        int id;
    };

    struct Student student = {1, "Ztiany", "China", 001};

    printf("int *p[10]的size = %d\n", sizeof(pi));//8*10=80
    printf("char *p[10]的size = %d\n", sizeof(pc));//8*10=80
    printf("float *p[10]的size = %d\n", sizeof(pf));//8*10=80
    printf("double *p[10]的size = %d\n", sizeof(pd));//8*10=80
    printf("student的size = %d\n", sizeof(student));//32
    printf("&student的size = %d\n", sizeof(&student));//8
}

static void pointerSample() {
    int a = 4;
    int *pa = &a;//取a的地址赋值给指针aP
    printf("指针 aP 的地址= %p\n", pa);
    printf("指针 aP 的值 = %d\n", *pa);
    *pa = 7;//取aP的地址，赋值，也就是给a赋值
    printf("变量 a = %d\n", a);
}
