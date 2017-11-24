/*
 ============================================================================
 
 Author      : Ztiany
 Description : C语言函数

 ============================================================================
 */


//如果自建的函数写在main函数的后面，需要在main函数之前声明这个函数
//声明的时候可以不写参数名，所以void printInt(char, int);也是可以的
void printInt(char name, int x);

int main() {
    printInt('a', 54);
}

void printInt(char name, int x) {
    printf("数据%c的值为%d \n", name, x);
}


//静态函数只能在内部调用
static inner_method() {

}

//c99内联函数
inline int swap(int *a, int *b) {
    int t = *a;
    *a = *b;
    *b = t;
}