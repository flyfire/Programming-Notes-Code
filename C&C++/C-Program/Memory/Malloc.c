#include <malloc.h>
#include <stdio.h>

/*
 ============================================================================
 
 Author      : Ztiany
 Description : 动态内存管理与指向它的指针变量

 ============================================================================
 */

void mallocSample();
void mallocSample2();
void voidTypePointer();
void voidTypePointerSample();

int main() {
    mallocSample();
    mallocSample2();
    voidTypePointer();
    voidTypePointerSample();
}

void voidTypePointerSample() {
    int *p1;
    p1 = (int *) malloc(5 * sizeof(int));
    for (int j = 0; j < 5; ++j) {
        scanf("%d", p1 + j);
    }
    printf("不及格的：");
    for (int i = 0; i < 5; ++i) {
        if (*(p1 + i) < 60) {
            printf("%d ", *(p1 + i));
        }
    }
}

void voidTypePointer() {
    //void指针类型 c99允许使用void指针类型，void不指向任何类型的数据，应该把其理解为执行向空类型或者不指向确定的类型的数据
    int a = 3;
    int *p1 = &a;
    char *p2;
    void *p3;
    p3 = (void *) p1;//p1转换为void指针类型
    p2 = (char *) p3;//void转换为char*类型
    printf("%d", *p1);
    p3 = &a;//赋值后得到的是纯地址，单并不指向a，不能通过*p3获取a的值。
    // printf("%d", *p3);//不合法，编译错误
}

void mallocSample2() {
    //calloc	(n, size)  n为元素个数，size为元素的长度，使用于为数组动态发分配空间
    void *memory = calloc(100, 4);//开启50*4的字节临时分配区域，返回起始地址，
    //以及分配的空间可以改变其大小，使用realloc重新分配
    realloc(memory, 800);
    free(memory);
}

void mallocSample() {
    //内存的动态分配，分配在堆中
    unsigned int size = 100;
    void *memory = malloc(size);//开启100个字节的临时分配空间，函数的返回值为第一个字节的地址，如果内存不足，返回NULL空指针
    printf("size = %d", sizeof(memory));// 4
    free(memory);//使用完后要释放，memory应该是最近一次调用calloc或malloc函数的返回值
}
