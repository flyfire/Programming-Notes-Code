/*
 ============================================================================
 
 Author      : Ztiany
 Description : 位域

 ============================================================================
 */

#include <stdio.h>

/*
 使用位域可以更好的利用内存空间。

 位域声明

 struct
    {
      type [member_name] : width ;
    };

type：整数类型，决定了如何解释位域的值。类型可以是整型、有符号整型、无符号整型。
member_name：位域的名称。
width：位域中位的数量。宽度必须小于或等于指定类型的位宽度。
 */

struct {
    unsigned int widthValidated;
    unsigned int heightValidated;
} status;//这种结构需要 8 字节的内存空间

//可以定义变量的宽度来告诉编译器，只使用几个字节
struct {
    unsigned int widthValidated : 1;
    unsigned int heightValidated : 1;
} status2;

int main() {
    printf("宽度 = %d \n", sizeof(status));// 8
    printf("宽度 = %d \n", sizeof(status2));// 4

    //测试位域
    struct {
        unsigned int age : 3;//3位，最大值为7。
    } Age;

    Age.age = 4;
    printf("Sizeof( Age ) : %d\n", sizeof(Age));
    printf("Age.age : %d\n", Age.age);

    Age.age = 7;
    printf("Age.age : %d\n", Age.age);

    Age.age = 8;//，如果您试图使用超过 3 位，则无法完成,此时age=0
    printf("Age.age : %d\n", Age.age);

    return 0;

}