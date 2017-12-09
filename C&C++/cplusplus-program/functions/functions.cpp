/*
 ============================================================================

 Author      : Ztiany
 Description : 函数

 ============================================================================
 */


#include <iostream>
#include <vector>

using namespace std;

//1：引用形参，通过传递引用形参，可以改变实参的值
static void reference_parameter1(int &i) {
    //我们改变了i的值，利用这个特性，可以把参数用作返回额外从信息的载体
    i = 4;
}

//2：把参数声明为引用类型，可以避免是实参被拷贝，这里只会传递实参的引用
static bool reference_parameter2(const string &s1, const string &s2) {
    return s1.size() > s2.size();
}

//3：const形参：如果参数不需要被修改，应该声明为const的
static void const_par1(const char *cp) {
    if (cp) {
        while (*cp) {
            cout << *cp << ", ";
            cp++;
        }
    }
    cout << endl;
}

//使用普通引用而非常量引用极大的限制了函数能接受的实参类型，这个函数只能接受非常量类型
static void const_par2(char *cp) {
    if (cp) {
        while (*cp) {
            cout << *cp << ", ";
            cp++;
        }
    }
    cout << endl;
}


//4：数组传参，这里的arr[10]只表示期望给一个长度为10 的数据，但实际长度却不确定
static void array_parameter1(int arr[10]) {
    cout << "array_parameter " << arr << endl;
}

//标准做法，传入头和尾，遍历数组
static void array_parameter2(const int *begin, const int *end) {
    while (begin != end) {
        begin++;
    }
}

//标准做法，传入数组和数组的长度
static void array_parameter3(const int *arr, size_t size) {

}







//initializer_list是标准中的类型，与vector类似， 它是一个模板，initializer_list中的对象永远是常量
static void initializer_parameter() {
    initializer_list<int> lst1;
    initializer_list<int> lst2{1, 2, 3, 4, 5, 6};
    initializer_list<int> lst3(lst2);//拷贝一个initializer_list，不会拷贝列表中的元素，拷贝后，原始列表和副本共享元素
    initializer_list<int> lst4 = lst2;//赋值一个initializer_list，不会拷贝列表中的元素，拷贝后，原始列表和副本共享元素
}


//返回引用
const static string &short_string(const string s1, const string s2) {
    return s1.size() > s2.size() ? s2 : s1;
}


//返回列表
static vector<string> get_message() {
    return {"A", "B", "C"};//列表转换为vector
}


//c++11尾置返回类型声明
static auto func(int i) -> int (*)[10];//该函数返回一个指针，指针指向含有十个元素的int数组


//使用decltype
static int odd[] = {1, 3, 5, 7, 9};
static int even[] = {0, 2, 4, 6, 8};

//decltype不负责把数组类型转换为对象的指针，所以返回值上需要加上*
decltype(even) *arrPtr(int i) {//返回一个指针，指针指向5个元素的数组
    return (i % 2) == 0 ? &even : &odd;
}


int main(int argc, char *argv[]) {
    //0：获取main函数的参数
    cout << argc << endl;
    for (int i = 0; i < argc; ++i) {
        cout << argv[i] << endl;
    }


    //1 reference_parameter1
    int intA = 1;
    reference_parameter1(intA);
    cout << "intA =" << intA << endl;

    //4 常量形参
    char *str1 = "abcdefg";
    const char *str2 = "hijklmn";
    const_par1(str1);
    const_par1(str2);
    const_par2(str1);
    //const_par2(str2);//Parameter type mismatch: Assigning 'const char *' to 'char *' discards const qualifier


    //2 array_parameter
    int array[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    array_parameter1(array);

}