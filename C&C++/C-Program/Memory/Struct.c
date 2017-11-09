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

int main() {
    //�ṹ��ʾ��
    defineStruct();
    //���ýṹ��
    referenceStruct();
    //�ṹ������
    structArray();
    //�ṹ��ָ��
    structPointer();
    //�ṹ���еĺ���ָ��
    functionPointerInStruct();
    return 0;
}

//�ڽṹ��������ָ�����
static void functionPointerInStruct() {
    struct Arr {
        int (*f)(int, int);
        int a;
        int b;
    };
    extern int maxValue(int, int);
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
    printf("%d", (*sp).num);
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

    //�ṹ���п��Զ�����һ���ṹ��
    struct Date {
        int month;
        int year;
        int day;
    };
    struct Man {
        char name[20];
        struct Date birthday;
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