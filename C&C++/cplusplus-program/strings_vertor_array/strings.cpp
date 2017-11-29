/*
 ============================================================================
 
 Author      : Ztiany
 Description : C++中的strings

 ============================================================================
 */

#include <string>
#include <iostream>
//cctype与ctypeh的内容是一样的，都是用来处理字符的头文件，cctype符合c++的规范。
#include <cctype>

using namespace std;

//定义和初始化字符串
static void defineString() {
    string str1;//空字符串
    string str2 = str1;//str2是str1的一个副本
    string str3 = "hi ya";//str3是该字符串字面常量的一个副本
    string str4(10, 'c');//str4的内容是 cccccccccc
    string str5("hi ya");//等价于str3

    //直接初始化与拷贝初始化
    //使用=初始化一个变量，实际上执行的是拷贝初始化，编译器把=号左边的值拷贝到新创建的对象中去
    //如果不使用=，则执行的是 直接初始化
    string str6 = string(10, 'a');//这种方式是创建一个临时对象，在执行拷贝初始化
}

//字符串的操作
static void stringOperation() {

    string str1 = "abcdefg";
    string str2 = "opqret";
    string str3;
    string str4;
    string str5;
    string str6 = "z";

    cout << str1 << endl;//输出字符串
    cin >> str3;//从cin中读取字符串赋值给str3，返回cin，字符串以空白分隔
    getline(cin, str4);//从cin中读取一行赋值给str4，getline连续读取字符，直到读到换行符为止。

    bool is_empty = str1.empty();//是否为空

    //字符串的size方法返回的类型是string::size_type
    //这在c++中很常见，string类和其他标准库类型都定义了几种配套类型，这些配套类型体现了与机器无关的特性
    //size_type就是其中一种，size_type是一种无符号类型，且保证其长度可以容纳string的size。
        string::size_type str1_size = str1.size();

    char first = str1[0];//获取单个字符

    str5 = str1 + str2;//连接字符串，创建一个新的字符串，+号要求相加的项中至少有一个是字符串对象，不能全部都是字符串常量
    str6 += str1;//把str1的内容追加到str6中去

    bool is_same = str1 == str2;//如果两个字符串中的内容完全一致，就返回true
    bool not_same = str1 != str2;
    //比较字符串，另外还有<、<=、>=
    bool greater = str1 > str2;
}

static void getline_sample() {
    string str;
    while (getline(cin, str)) {
        if (!str.empty()) {
            cout << str << endl;
        } else {
            break;
        }
    }
}


//处理字符串中的字符
static void process_string() {

    string str1 = "abcdefghijklnmopqretuvwxyz";

    //编译字符串方式1
    for (auto c:str1) {
        cout << c;
        if (c != 'z') {
            cout << ", ";
        } else {
            cout << endl;
        }
    }

    string str2 = "hello, hi, wow!!!";
    //统计字符串中的标点符号
    decltype(str2.size()) punct_count = 0;
    for (auto c:str2) {
        if (ispunct(c)) {//ispunct在cctype中，判断字符是否为标点符号
            ++punct_count;
        }
    }
    cout << "punct_count = " << punct_count << endl;

    //使用for循环改变字符串中的内容
    for (auto &c:str1) {//这里c是一个引用
        c = toupper(c);
    }
    cout << "new str1 = " << str1 << endl;
    str1[0] = tolower(str1[0]);
    cout << "new str1 = " << str1 << endl;


    //使用下边执行迭代
    for (decltype(str1.size()) index = 0; index != str1.size() && !isspace(str1[index]); ++index) {
        str1[index] = tolower(str1[index]);
    }
    cout << "new str1 = " << str1 << endl;
}

//把输入的数字转换为16进制表示
static void sample() {
    const string hex_digits = "0123456789ABCDEF";
    cout << "enter a series of number between 0 and 15"
         << "separated by spaces" << endl;
    string result;
    string::size_type n;
    while (cin >> n) {
        if (n < hex_digits.size()) {
            result += hex_digits[n];
        }
    }
    cout << "result = " << result << endl;
}


int main() {
    //getline_sample();
    //process_string();
    sample();
    return EXIT_SUCCESS;
}