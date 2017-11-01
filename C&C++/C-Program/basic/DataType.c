/*
 ============================================================================
 Author      : Ztiany
 Description : C语言的数据类型
 ============================================================================
 */

#include <stdio.h>//声明头文件，即应用程序接口，类似Java的导包
#include <stdbool.h>//支持布尔类型的头文件

#define A 1.3//定义常量

const int days[3] = {1, 2, 3};//const定义常量更灵活，可以定义数组

void dataType();//如果函数写在main后面，需要在main函数前面先声明这个函数
void memoryAddress();

//C程序一定是从主函数开始执行的。main函数的返回值为int类型.
int main() {
    dataType();//数据类型与数据类下大小
    memoryAddress();//获取内存地址
    return 1;
}

/*
 * 数类型
 */
void dataType() {

    char c = 'c';
    short hd = 1233;
    int d = 12312312;
    long long ld = 232423423;
    long sld = 432432;
    float f = 1212.132121F;
    double lf = 232.222222222;
    bool flag = false;    //布尔类型，c99添加
    _Bool b1 = 1;
    _Bool b2 = 0;

    //打印变量
    printf("%c\n", c);
    printf("%ld\n", sld);
    printf("%hd\n", hd);
    printf("%d\n", d);
    printf("%lld\n", ld);
    printf("%f\n", f);
    printf("%lf\n", lf);

    printf("字符的长度是:%d\n", sizeof(char));
    printf("short的长度是:%d\n", sizeof(short));
    printf("int的长度是:%d\n", sizeof(int));
    printf("long的长度是:%d\n", sizeof(long));
    printf("long long的长度是:%d\n", sizeof(long long));
    printf("float的长度是:%d\n", sizeof(float));
    printf("double的长度是:%d\n", sizeof(double));

    printf("常量A=%f", A);
}


//获取与打印内存地址
void memoryAddress() {
    int cc = 3;
    printf("%p\n", &cc);
}


