/*
 ============================================================================
 
 Author      : Ztiany
 Description : 

 ============================================================================
 */

#include <stdio.h>

static void passArray(int intArr[]) {
    int size = sizeof(intArr);//8

    printf("stack size = %d \n", size);
}


static void array() {
    int intArr[10];
    int size = sizeof(intArr);//40
    int length = size / sizeof(int);
    printf("stack size = %d \n", size);
    printf("length = %d \n", length);
    passArray(intArr);
}

int main() {
    array();
    return 0;
}