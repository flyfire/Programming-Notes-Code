/*
 ============================================================================
 
 Author      : Ztiany
 Description : 指针处理链表

 ============================================================================
 */
#include <malloc.h>
#include <stdio.h>


struct Student {
    long num;
    float score;
    struct Student *next;
};

#define SIZE sizeof(struct Student)
int n;

struct Student *create(void) {
    struct Student *head;
    struct Student *p1, *p2;
    n = 0;
    p1 = p2 = (struct Student *) malloc(SIZE);//指向同一个空间
    scanf("%ld,%f", &p1->num, &p1->score);//初始化字段
    head = NULL;

    while (p1->num != 0) {
        n = n + 1;
        
        if (n == 1) {//第一次
            head = p1;
        } else {//之后
            p2->next = p1;
        }
        p2 = p1;
        p1 = (struct Student *) malloc(SIZE);
        scanf("%ld,%f", &p1->num, &p1->score);
    }

    p2->next = NULL;
    return head;
}

int main() {
    struct Student *pt;
    pt = create();
    printf("%ld , %f", pt->num, pt->score);
    return EXIT_SUCCESS;
}
