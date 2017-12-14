/*
 ============================================================================
 
 Author      : Ztiany
 Description : C++函数返回类型

 ============================================================================
 */
#include <cstdlib>
#include <string>
#include <vector>

using namespace std;

//返回值，拷贝值
const static string short_string(const string &s1, const string &s2) {
    return s1.size() > s2.size() ? s2 : s1;
}

//返回引用，不拷贝值
const static string &short_string(const string s1, const string s2) {
    return s1.size() > s2.size() ? s2 : s1;
}

//返回列表
static vector<string> get_message() {
    return {"A", "B", "C"};//列表转换为vector
}


//严重错误：返回局部变量的引用
static string &get_string() {
    string ret;
    if (ret.empty()) {
        ret = "empty";
    }
    return ret;
}

//返回数组指针
typedef int arrI1[10];
using arrI2 = int[10];

static arrI1* get_integers1();
static arrI2* get_integers2();
static int* get_integers3();


int main() {

    return EXIT_SUCCESS;
}


