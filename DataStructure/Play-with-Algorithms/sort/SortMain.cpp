#include "SortTestHelper.h"

/**选择排序，O(n^2)*/
template<typename T>
void selectSort(T *arr, int length) {
    for (int i = 0; i < length; ++i) {
        int minIndex = i;
        //每次找到最小值索引
        for (int j = i + 1; j < length; ++j) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        //每次找到的最小值放到前面
        swap(arr[i], arr[minIndex]);
    }
}

/**插入排序，O(n^2)*/
template<typename T>
void insertSort(T *arr, int length) {
    int j;
    for (int i = 1; i < length; ++i) {
        //右边无序第一个数
        T temp = arr[i];
        for (j = i; j > 0 && arr[j - 1] > temp; j--) {
            //有序序列往右边移
            arr[j] = arr[j - 1];
        }
        arr[j] = temp;
    }
}

int main() {
    int *arr = SortTextHelper::generateRandomArray(100, 10, 200);
    SortTextHelper::printArray(arr, 100);
    SortTextHelper::testSort("insertSort", insertSort, arr, 100);
    SortTextHelper::printArray(arr, 100);
    delete[] arr;
    return EXIT_SUCCESS;
}