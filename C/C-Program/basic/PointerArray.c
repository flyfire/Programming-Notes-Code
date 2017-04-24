/*
 ============================================================================
 
 Author      : Ztiany
 Description : 指针数组和多重指针

 ============================================================================
 */



void pointerArray();

void pointerArray2();


int main() {
    pointerArray();
    pointerArray2();
}

void pointerArray2() {
    int a[5] = {1, 2, 4, 5, 6};
    int *pA[5] = {&a[0], &a[1], &a[2], &a[3], &a[4]};
    int **p = pA;
    for (int i = 0; i < 5; ++i) {
        printf("%d , ", **p);
        p++;
    }
}

//指针数组
void pointerArray() {
    // int *a[4] 表示一个一维指针数组，数组里面的元素都是指针类型，注意int (*a)[4]表示的是指向四个元素的一维数组的指针
    //定义方式 类型名 * 数组名[4];
    //指针数组适用于字符串数据的情况
    char *bookList[4] = {"CoreJava", "EffectiveJava", "Android", "C ++"};
    for (int i = 0; i < 4; ++i) {
        printf("%s\n", bookList[i]);
    }

    //指向指针的指针
    char **p = bookList;
    printf("%s", *(p + 1));

}