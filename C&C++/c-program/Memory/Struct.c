/*
 ============================================================================

 Author      : Ztiany
 Description : C���Խṹ��

 ============================================================================
 */
#include <stdio.h>
#include <mem.h>

static void defineStruct();
static void referenceStruct();
static void structArray();
static void structPointer();
static void functionPointerInStruct();
static void traverseStruct();

int main() {
    //�ṹ��ʾ��
    //defineStruct();
    //���ýṹ��
    //referenceStruct();
    //�ṹ������
    //structArray();
    //�ṹ���������
    traverseStruct();
    //�ṹ��ָ��
    //structPointer();
    //�ṹ���еĺ���ָ��
    //functionPointerInStruct();
    return 0;
}

//�ڽṹ��������ָ�����
static void functionPointerInStruct() {
    struct Arr {
        int (*f)(int, int);
        int a;
        int b;
    };

    extern int maxValue(int, int);//������������������ʱ�Զ�����

    int (*f)(int, int) = maxValue;
    struct Arr arr = {f, 3, 100};
    int max = arr.f(arr.a, arr.b);
    printf("max value = %d", max);
}

int maxValue(int a, int b) {
    if (a > b) {
        return a;
    }
    return b;
}

//�ṹ��ָ��
static void structPointer() {
    //����ṹ��ָ��
    struct Student {
        int num;                    //4
        char name[20];              //20
        char sex;                   //1
        int age;                    //4
        float score;                //4
        char address[20];           //20
    } student = {20, "����", 'm', 30, 23.6F, "����"};

    struct Student *sp;
    sp = &student;
    student.num = 1;
    strcpy(student.name, "����");
    printf("%s,%d\n", sp->name, sp->num);// ʹ�ýṹ��ָ�����ýṹ�������ʱ��ֱ��ʹ�� ->����
    printf("%d\n", (*sp).num);
}

//����ṹ������
static void structArray() {
    //ʹ�ýṹ�����飬���ﶨ����3��Ԫ�ص�����
    struct Man {
        char name[20];              //20
        int num;                    //4
    } players[] = {"z", 0, "y", 0, "x", 0};

    char name[20];
    for (int i = 0; i < 10; ++i) {
        scanf("%s", name);
        for (int j = 0; j < 3; ++j) {
            if (strcmp(name, players[j].name) == 0) {
                players[j].num++;
            }
        }
    }
    for (int k = 0; k < 3; ++k) {
        printf("name : %s , num = %d \n", players[k].name, players[k].num);
    }
}


//�ṹ�����
static void traverseStruct() {

    struct Man {
        char name[20];
        int age;
    };

    struct Man mans[] = {{"Jack", 20}, {"Rose", 19}};
    //�����ṹ������

    //1.
    struct Man *p = mans;
    for (; p < mans + 2; p++) {
        printf("%s,%d \n", p->name, p->age);
    }

    //2.
    int i = 0;
    for (; i < sizeof(mans) / sizeof(struct Man); i++) {
        printf("%s,%d \n", mans[i].name, mans[i].age);
    }
}


//���ʽṹ��ĳ�Ա
static void referenceStruct() {
    struct Student {
        int num;            //4
        char name[20];      //20
        char sex;           //1
        int age;            //4
        float score;        //4
        char address[20];   //20
    } student = {20, "����", 'm', 30, 23.6F, "����"};

    printf("student address is %p \n", &student);   //�ṹ���׵�ַ
    printf("student address is %p \n", &student.num);
}


//����ṹ��
static void defineStruct() {

    //����ֻ�ܴ洢������ͬ��Ԫ�أ������Ҫ��һ�����ݽ���д洢��ͬ���������ͣ�����ʹ�ýṹ��
    struct Student {
        //����Ԫ�ؿ��Գ�Ϊ����Ա�б�����
        int num;            //4
        char name[20];      //20
        char sex;           //1
        int age;            //4
        float score;        //4
        char address[20];   //20
    };

    printf("Student size is %d \n", sizeof(struct Student));//56

    //�ṹ��Ƕ�ף��ṹ���п��Զ�����һ���ṹ��
    //Ƕ��1
    struct Date {
        int month;
        int year;
        int day;
    };
    struct Man {
        char name[20];
        struct Date birthday;
    };

    //Ƕ��2
    struct A {
        char name[20];
        int age;
        struct B {
            char name[20];
        } t;
    };

    //�����ṹ��ֻ�Ƕ�����һ������ģ�ͣ���û�ж���������䱾��ռ�ڴ棬���ڶ���һ���ṹ���ʵ�����Ż�ռ���ڴ�
    struct Student student = {20, "����", 'm', 30, 23.6F, "����"};
    printf("student age = %d \n", student.age);


    //��ʽ2
    struct Date1 {
        int month;
        int year;
        int day;
    } date1, date2;


    //��ʽ3:�����ṹ��
    struct {
        int month;
        int year;
        int day;
    } date3;

}