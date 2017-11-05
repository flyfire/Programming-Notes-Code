#include <stdio.h>

void enumSample();

/*
 ============================================================================
 
 Author      : Ztiany
 Description : c语言枚举

 ============================================================================
*/

int main() {
    enumSample();
}

void enumSample() {

    //如果一个变量只有集中可能的值，可以定义枚举类型
    //c语言对枚举的元素按照常量处理，每一个枚举都是int类型，默认从0开始,
    enum Weekday {
        sun, mon, tue, wed, thu, fri, sta
    };
    enum Weekday day1 = sta;
    printf("%d", day1);

    //可以指定枚举的值
    enum Enum {
        a = 3, b = 5
    };

}
