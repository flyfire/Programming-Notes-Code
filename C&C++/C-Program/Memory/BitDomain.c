/*
 ============================================================================
 
 Author      : Ztiany
 Description : �ṹ�壺λ��

 ============================================================================
 */

#include <stdio.h>


struct {
    unsigned int widthValidated;
    unsigned int heightValidated;
} status1;//���ֽṹ��Ҫ8�ֽڵ��ڴ�ռ�

//���Զ�������Ŀ�������߱�������һ���ֽ���8λ
struct {
    unsigned int widthValidated : 2;//ʹ��2λ
    unsigned int heightValidated : 2;//ʹ��2λ
} status2;

int main() {

    printf("status1��� = %d \n", sizeof(status1));// 8
    printf("status2��� = %d \n", sizeof(status2));// 4

    //����λ��Age
    struct {
        unsigned int age : 3;//3λ�����ֵΪ7��
    } Age;

    Age.age = 4;
    printf("Sizeof( Age ) : %d\n", sizeof(Age));
    printf("Age.age : %d\n", Age.age);

    Age.age = 7;
    printf("Age.age : %d\n", Age.age);

    Age.age = 8;//���������ͼʹ�ó��� 3 λ�����޷����,��ʱage=0
    printf("Age.age : %d\n", Age.age);

    //����λ������λ��
    struct {
        int a :4;
        int b :3;
        int :1;//����λ��
        int d:8;
    };

    return 0;

}