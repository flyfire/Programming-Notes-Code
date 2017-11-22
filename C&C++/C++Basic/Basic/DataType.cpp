//引入io
#include <iostream>
//引入数据类型限制头文件
#include <climits>
#include <vector>

void ioSample();

void dataType();

int main() {
    ioSample();
    dataType();
    return 0;
}

//输出输出示例
void ioSample() {
    using namespace std;
    int getNum;
    cin >> getNum;
    cout << "the num is " << getNum << endl;
    //put用于输出字符
    std::cout.put('a').put('\n');
}

//C++的数据类型
void dataType() {

    //声明变量
    int a; //a未知的
    int b(2);
    int c{3};
    int d = {4};
    int e{};// e = 0，C++11列表初始化
    unsigned int f;
    unsigned quarterback;//不指定类型，默认是int
    int g = 02;//八进制
    int h = 0xF;//十六进制
    char i = 'a';
    int j = i;
    //字符：wchar底层的类型取决于编译器实现，可能是unsigned short或者是int
    wchar_t w_char = '我';
    //C11：char16_t和char32_t是内置类型，但是底层类型可能跟随系统
    char16_t char_16 = u'q';
    char32_t char_32 = U'我';
    //布尔
    bool is_real = true;
    bool stop = 1;
    bool start = 0;
    //constant
    const int k = 32;
    //float，浮点常量默认为double
    float l = 32.3;
    double n = 32.323;
    float m = 32.333F;

    //类型转换
    int o = (int) (33.3);
    int p = (int) 32.3;
    //int q = {32.3}; //{}很严格，不允许

    //auto，c++11中的auto是让c+=自己判断类型
    std::vector<double> scores;
    std::vector<double>::iterator pv = scores.begin();
    //c++11允许将上面代码重写为
    auto auto_pv = scores.begin();


    //数据类型的大小
    std::cout << "sizeof(int) = " << sizeof(int) << std::endl;
    std::cout << "INT_MIN = " << INT_MIN << std::endl;
    std::cout << "INT_MAX = " << INT_MAX << std::endl;
    std::cout << "CHAR_BIT = " << CHAR_BIT << std::endl;
    std::cout << "LONG_MAX = " << LONG_MAX << std::endl;
}