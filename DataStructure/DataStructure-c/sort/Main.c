/*
 ============================================================================

 Author      : Ztiany
 Description : 排序

 ============================================================================
 */


#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define  MAX 100

long getSystemTime() {
    struct timeb tb;
    ftime(&tb);
    return tb.time * 1000 + tb.millitm;
}

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void printArray(int *arr, int count) {
    printf("print array begin \n");
    for (int i = 0; i < count; ++i) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

/**冒泡排序*/
void bubbleSort(int *arr, int size) {
    long start = getSystemTime();

    for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size - i - 1; ++j) {
            if (arr[j] > arr[j + 1]) {
                swap(&arr[j], &arr[j + 1]);
            }
        }
    }

    printf("bubbleSort count %d use time: %ld \n", size, (getSystemTime() - start));
}

/**选择排序*/
void selectSort(int *arr, int size) {
    long start = getSystemTime();

    //每一次遍历的最小值索引
    int min;
    for (int i = 0; i < size; ++i) {

        min = i;

        for (int j = i + 1; j < size; ++j) {
            if (arr[j] < arr[min]) {
                min = j;
            }
        }

        if (min != i) {
            swap(&arr[min], &arr[i]);
        }
    }

    printf("selectSort count %d use time: %ld \n", size, (getSystemTime() - start));
}


/**插入排序：将无序序列插入到有序序列中，在基本有序的序列中非常高效，数据序列少的情况下也比较高效*/
void insertSort(int *arr, int size) {
    long start = getSystemTime();

    //第一次把第一个数当作是有序的序列
    int j;
    for (int i = 1; i < size; ++i) {

        //如果右边的无序序列的第一个数 小于左边的有序序列的最后一个数，开始插入
        if (arr[i] < arr[i - 1]) {
            //记住无序序列的第一个数
            int temp = arr[i];
            //直到遇到不必这个数小位置，插入到这个数之前
            for (j = i - 1; j >= 0 && temp < arr[j]; --j) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }

    printf("insertSort count %d use time: %ld \n", size, (getSystemTime() - start));
}

/**希尔排序，对数据进行分段，然后每段运用插入排序*/
void shellSort(int *arr, int size) {
    long start = getSystemTime();

    int step = size;
    int i, j, k;

    do {
        step = step / 3 + 1;
        //分成step组，每组遍历

        for (i = 0; i < step; i++) {
            printf("step = %d \n", step);
            for (j = i + step; j < size; j += step) {

                if (arr[j] < arr[j - step]) {

                    int temp = arr[j];
                    for (k = j - step; k >= 0 && temp < arr[k]; k -= step) {
                        arr[k + step] = arr[k];
                    }

                    arr[k + step] = temp;
                }

            }

        }

    } while (step > 1);


    printf("shellSort count %d use time: %ld \n", size, (getSystemTime() - start));

}

int main() {

    int arr[MAX];
    srand((unsigned int) time(NULL));
    for (int i = 0; i < MAX; i++) {
        arr[i] = rand() % MAX;
    }

//    bubbleSort(arr, MAX);
//    selectSort(arr, MAX);
//    insertSort(arr, MAX);
    shellSort(arr, MAX);
//    printArray(arr, MAX);

    return EXIT_SUCCESS;
}