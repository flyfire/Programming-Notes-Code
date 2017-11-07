#include <malloc.h>
#include <stdio.h>

/*
 ============================================================================
 
 Author      : Ztiany
 Description : ��̬�ڴ������ָ������ָ�����

 ============================================================================
 */

void mallocSample();
void mallocSample2();
void voidTypePointer();
void voidTypePointerSample();

int main() {
    mallocSample();
    mallocSample2();
    voidTypePointer();
    voidTypePointerSample();
}

void voidTypePointerSample() {
    int *p1;
    p1 = (int *) malloc(5 * sizeof(int));
    for (int j = 0; j < 5; ++j) {
        scanf("%d", p1 + j);
    }
    printf("������ģ�");
    for (int i = 0; i < 5; ++i) {
        if (*(p1 + i) < 60) {
            printf("%d ", *(p1 + i));
        }
    }
}

void voidTypePointer() {
    //voidָ������ c99����ʹ��voidָ�����ͣ�void��ָ���κ����͵����ݣ�Ӧ�ð������Ϊִ��������ͻ��߲�ָ��ȷ�������͵�����
    int a = 3;
    int *p1 = &a;
    char *p2;
    void *p3;
    p3 = (void *) p1;//p1ת��Ϊvoidָ������
    p2 = (char *) p3;//voidת��Ϊchar*����
    printf("%d", *p1);
    p3 = &a;//��ֵ��õ����Ǵ���ַ��������ָ��a������ͨ��*p3��ȡa��ֵ��
    // printf("%d", *p3);//���Ϸ����������
}

void mallocSample2() {
    //calloc	(n, size)  nΪԪ�ظ�����sizeΪԪ�صĳ��ȣ�ʹ����Ϊ���鶯̬������ռ�
    void *memory = calloc(100, 4);//����50*4���ֽ���ʱ�������򣬷�����ʼ��ַ��
    //�Լ�����Ŀռ���Ըı����С��ʹ��realloc���·���
    realloc(memory, 800);
    free(memory);
}

void mallocSample() {
    //�ڴ�Ķ�̬���䣬�����ڶ���
    unsigned int size = 100;
    void *memory = malloc(size);//����100���ֽڵ���ʱ����ռ䣬�����ķ���ֵΪ��һ���ֽڵĵ�ַ������ڴ治�㣬����NULL��ָ��
    printf("size = %d", sizeof(memory));// 4
    free(memory);//ʹ�����Ҫ�ͷţ�memoryӦ�������һ�ε���calloc��malloc�����ķ���ֵ
}
