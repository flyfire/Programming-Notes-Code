/*
 ============================================================================
 
 Author      : Ztiany
 Description : λ��

 ============================================================================
 */

#include <stdio.h>

/*
 ʹ��λ����Ը��õ������ڴ�ռ䡣

 λ������

 struct
    {
      type [member_name] : width ;
    };

type���������ͣ���������ν���λ���ֵ�����Ϳ��������͡��з������͡��޷������͡�
member_name��λ������ơ�
width��λ����λ����������ȱ���С�ڻ����ָ�����͵�λ��ȡ�
 */

struct {
    unsigned int widthValidated;
    unsigned int heightValidated;
} status;//���ֽṹ��Ҫ 8 �ֽڵ��ڴ�ռ�

//���Զ�������Ŀ�������߱�������ֻʹ�ü����ֽ�
struct {
    unsigned int widthValidated : 1;
    unsigned int heightValidated : 1;
} status2;

int main() {
    printf("��� = %d \n", sizeof(status));// 8
    printf("��� = %d \n", sizeof(status2));// 4

    //����λ��
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

    return 0;

}