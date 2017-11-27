/*
 ============================================================================
 
 Author      : Ztiany
 Description : 复合类型：引用和指针

 ============================================================================
 */

#include "cstdlib"
#include "iostream"

using namespace std;

void reference() {
    int value_a = 323;
    int &ref_a = value_a;//ref_a指向value_a
    //int &ref_b; //报错，引用类型必需被初始化
    ref_a = 3;
    cout << "value_a = " << value_a << endl;
}

void pointer() {
    int value_a = 32;
    int *pA = &value_a;
    void *pVoid = &value_a;
    int *&refPa = pA;//refPa是对指针pA的引用
    cout << "value_a address = " << pA << endl;

    const int *pB = &value_a;//pA是一个指向整型常量的指针
    int *const pC = &value_a;//pC是一个常量指针
}

void cons_reference() {
    int value_a = 3;
    const int &ref_a1 = value_a;
    const int &ref_a2 = 3;
    const int &ref_a3 = ref_a1 * 3;

    const int value_b = 3;
    //int &ref_b = value_b; //编译错误
}

constexpr int size_a() {
    return 333;
}

void constexpr_type() {
    constexpr int value_b = 3243;
    constexpr int value_c = value_b + 3;
    constexpr int sz = size_a();//只有size_a是一个constexpr函数是才是一条正确的声明语句
}



void process_type() {
    //使用typedef定义类型别名
    typedef int Integer;
    typedef const char *String;

    //使用auto定义变量
    int value_a = 3, value_b = 4;
    auto value_c = value_a + value_b;//编译器推断出value_c的类型是int类型
    auto pA = &value_a;//编译器推断出value_c的类型是int类型指针
    int *pB = pA;
    int value_d = value_c;


    //使用delctype定义变量
    const int ci = 0, &cj = ci;
    decltype(ci) x = 0;//x的类型是const int
    decltype(cj) y = x;//y的类型是const int&，y绑定到了x
    //decltype(cj) z; //decltype(cj)是引用类型，必需初始化

    int i = 4, *p = &i, &r = i;
    decltype(r + 0) b;
    // decltype(*p) c; //错误，decltype(*p)是引用类型，必需被初始化。
    decltype(i) j;
    decltype((i)) rB;
}

int main() {
    reference();
    pointer();
    constexpr_type();
    process_type();
    return EXIT_SUCCESS;
}