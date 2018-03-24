/*
 ============================================================================
 
 Author      : Ztiany
 Description : 

 ============================================================================
 */
#include "DynamicArray.h"
#include "LinkedList.h"
#include "CompaniesLinkedList.h"

/**
 * 动态数组
 */
void testDynamicArray() {
    //初始化动态数组
    Dynamic_Array *myArray = Init_Array();
    //打印容量
    printf("array capacity: %d\n", Capacity_Array(myArray));
    printf("array size: %d\n", Size_Array(myArray));

    //插入元素
    for (int i = 0; i < 30; i++) {
        PushBack_Array(myArray, i);
    }
    printf("array capacity: %d\n", Capacity_Array(myArray));
    printf("array size: %d\n", Size_Array(myArray));

    //打印
    Print_Array(myArray);

    //删除
    RemoveByPos_Array(myArray, 0);
    RemoveByValue_Array(myArray, 27);

    //打印
    Print_Array(myArray);

    //查找5个位置
    int pos = Find_Array(myArray, 5);
    printf("5 fined :pos:%d %d\n", pos, At_Array(myArray, pos));

    //销毁
    FreeSpace_Array(myArray);
}

//自定义数据类型
typedef struct PERSON {
    char name[64];
    int age;
    int score;
} Person;

//打印函数
void PrintPerson(void *data) {
    Person *p = (Person *) data;
    printf("Name:%s Age:%d Score:%d\n", p->name, p->age, p->score);
}

/**
 * 链表
 */
void testLinked() {

    //创建链表
    LinkList *list = Init_LinkList();

    //创建数据
    Person p1 = {"aaa", 18, 100};
    Person p2 = {"bbb", 19, 99};
    Person p3 = {"ccc", 20, 101};
    Person p4 = {"ddd", 17, 97};
    Person p5 = {"eee", 16, 59};

    //数据插入链表
    Insert_LinkList(list, 0, &p1);
    Insert_LinkList(list, 0, &p2);
    Insert_LinkList(list, 0, &p3);
    Insert_LinkList(list, 0, &p4);
    Insert_LinkList(list, 0, &p5);

    //打印
    Print_LinkList(list, PrintPerson);

    //删除3
    RemoveByPos_LinkList(list, 3);

    //打印
    printf("---------------\n");
    Print_LinkList(list, PrintPerson);

    //返回第一个结点
    printf("-----find result------------\n");
    Person *ret = (Person *) Front_LinkList(list);
    printf("Name:%s Age:%d Score:%d\n", ret->name, ret->age, ret->score);

    //销毁链表
    FreeSpace_LinkList(list);
}


typedef struct CPERSON {
    CLinkNode node;
    char name[64];
    int age;
} CPerson;

void PrintCPerson(CLinkNode *data) {
    CPerson *p = (CPerson *) data;
    printf("Name:%s Age:%d\n", p->name, p->age);
}

int CompareCPerson(CLinkNode *node1, CLinkNode *node2) {
    CPerson *p1 = (CPerson *) node1;
    CPerson *p2 = (CPerson *) node2;
    if (strcmp(p1->name, p2->name) == 0 && p1->age == p2->age) {
        return 0;
    }
    return -1;
}

/**
 * 企业链表
 */
void testCompaniesLinkedList() {

    //创建链表
    CLinkList *list = Init_CLinkList();

    //创建数据
    CPerson p1, p2, p3, p4, p5;
    strcpy(p1.name, "aaa");
    strcpy(p2.name, "bbb");
    strcpy(p3.name, "ccc");
    strcpy(p4.name, "ddd");
    strcpy(p5.name, "eee");

    p1.age = 10;
    p2.age = 20;
    p3.age = 30;
    p4.age = 40;
    p5.age = 50;

    //将结点插入链表
    Insert_CLinkList(list, 0, (CLinkNode *) &p1);
    Insert_CLinkList(list, 0, (CLinkNode *) &p2);
    Insert_CLinkList(list, 0, (CLinkNode *) &p3);
    Insert_CLinkList(list, 0, (CLinkNode *) &p4);
    Insert_CLinkList(list, 0, (CLinkNode *) &p5);

    //打印
    Print_CLinkList(list, PrintCPerson);

    //删除结点
    Remove_CLinkList(list, 2);

    //打印
    printf("---------------\n");
    Print_CLinkList(list, PrintCPerson);

    //查找
    CPerson findP;
    strcpy(findP.name, "bbb");
    findP.age = 20;
    int pos = Find_CLinkList(list, (CLinkNode *) &findP, CompareCPerson);
    printf("---------------\n");
    printf("position:%d\n", pos);

    //释放链表内存
    FreeSpace_CLinkList(list);
}

int main() {
//    testDynamicArray();
//    testLinked();
    testCompaniesLinkedList();
    return EXIT_SUCCESS;
}
