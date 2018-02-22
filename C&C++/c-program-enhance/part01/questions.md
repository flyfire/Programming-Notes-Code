1. 数据类型的本质是什么？ （从编译器的角度考虑）

2. 如何为一个数据类型起别名？

3. 既然有栈空间，为何要有堆空间？


    栈的空间由谁分配和回收？
    堆的空间又由谁分配和回收？

4. 有一个函数，数组做函数函数


    void print_array(int a[128])
    {
        //sizeof(a) = ?
    }

5. 有一个数组，如何求出元素个数？

    int main(void)
    {
        int a[] = {1, 5, 7, 0, 10, 7, 3};
    
    }


6. 在32位平台上


    char ************p = NULL;
    int *q = NULL;
    sizeof(p) = ?
    sizeof(q) = ?


7. 画出下面代码的内存四区图
    
    
    #include <stdio.h>
    #include <string.h>
    #include <stdlib.h>
    
    char *get_mem(int size)
    {
        char *p2 = NULL;            //分配4个字节的内存 栈区也叫临时区
        p2 = (char *)malloc(size);
    
        return p2;
    }
    
    int main(void)
    {
        char buf[100];
        int a = 10;     //分配4个字节的内存 栈区也叫临时区
        int *p;         //分配4个字节的内存
        p = &a;
    
        *p = 20;
    
        char *mp = get_mem(100);
        strcpy(mp, "ABCDEFG");
    
        if (mp != NULL)
        {
            free(mp);
            mp = NULL;
        }
    
        return 0;
    }
