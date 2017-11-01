/*
 ============================================================================
 
 Author      : Ztiany
 Description : 使用typedef声明新类型

 ============================================================================
 */

// 可以使用typedef指定新的类型名来代替已有的类型名

/*
 定义一个新的类型的方法是：
 1. 先按定义变量的方法写出定义体(int a)
 2.将变量名换成新的类型名(Count)
 3.在最前面加上typedef(typedef Count)
 4.然后可以用新的类型名去定义变量

 其他：
 1.习惯把typedef声明的类型的第一次字母大写
 2.typedef与define表面上有相似之处，但是本质不一样，define在与编译时处理，而typedef在编译时处理。
 */

#include <stdio.h>

//1:简单的使用新的类型名代替原有类型名
typedef int Integer;
typedef float Real;
typedef int Count;

//2:命名一个简单的名称代替复杂的类型表示方法

typedef struct {//给结构体定义一个Date的类型名称
    int year;
    int month;
    int day;
} Date;
Date date;

typedef int Num[100];//声明Num为整型数组类型名
Num num = {1, 2, 3, 4, 5};

typedef char *String;//声明String为字符指针名
String s = "abc";

typedef int (*Pointer)(); //声明Pointer为指向函数指针的类型，该函数返回int类型
Pointer pointer;

//3：typedef与define
#define int Int;//使用宏定义可以对数据类型取别名


int main() {
    Count a = 3;
    Real b = 3.0F;
    //结构体
    date.day = 10;
    //函数指针别名
    int get();
    pointer = get;
    printf("%d", pointer());
}

int get() {
    return 9;
}
