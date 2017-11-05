/*
 ============================================================================
 
 Author      : Ztiany
 Description : C语言函数

 ============================================================================
 */

void printInt(char name, int x);//声明的时候可以不写参数名，所以void printInt(char, int);也是可以的

int main() {
    printInt('a', 54);
}

void printInt(char name, int x) {
    printf("数据%c的值为%d \n", name, x);
}

static inner_method(){//静态函数只能在内部调用

}