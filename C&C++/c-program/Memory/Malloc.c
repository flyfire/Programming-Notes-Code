#include <malloc.h>
#include <stdio.h>

/*
 ============================================================================
 
 Author      : Ztiany
 Description : ��̬�ڴ������ָ������ָ�����

 ============================================================================
 */

void mallocSample();
void callocSample();
void voidTypePointerSample1();
void voidTypePointerSample2();

int main() {
    mallocSample();
    callocSample();
    voidTypePointerSample1();
    voidTypePointerSample2();
}

void voidTypePointerSample1() {
    int *p1 = NULL;
    //�����ڲ��ǿתΪint���͵�ָ��
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
    printf("\n");
}

void voidTypePointerSample2() {
    //voidָ������ c99����ʹ��voidָ�����ͣ�void��ָ���κ����͵����ݣ�Ӧ�ð������Ϊִ��������ͻ��߲�ָ��ȷ�������͵�����
    int a = 3;
    int *p1 = &a;
    char *p2 = NULL;
    void *p3 = NULL;
    p3 = (void *) p1;//p1ת��Ϊvoidָ������
    p2 = (char *) p3;//voidת��Ϊchar*����
    printf("%d", *p1);

    p3 = &a;//��ֵ��õ����Ǵ���ַ��û��������Ϣ������ͨ��*p3��ȡa��ֵ��
    //printf("%d", *p3);//���Ϸ����������
}

void mallocSample() {
    //�ڴ�Ķ�̬���䣬�����ڶ���
    unsigned int size = 100;
    void *memory = malloc(size);//����100���ֽڵ���ʱ����ռ䣬�����ķ���ֵΪ��һ���ֽڵĵ�ַ������ڴ治�㣬����NULL��ָ��
    printf("size = %d \n", sizeof(memory));// 64λϵͳ = 8
    free(memory);//ʹ�����Ҫ�ͷţ�memoryӦ�������һ�ε���calloc��malloc�����ķ���ֵ
    memory = NULL;//�ÿ��Ǹ���ϰ��
}

void callocSample() {
    //calloc(n, size)  nΪԪ�ظ�����sizeΪԪ�صĳ��ȣ�ʹ����Ϊ���鶯̬������ռ�
    void *memory = calloc(100, 4);//����100*4���ֽ���ʱ�������򣬷�����ʼ��ַ��
    //�Ѿ�����Ŀռ���Ըı����С��ʹ��realloc���·��䣬������СҲ��������
    realloc(memory, 800);
    printf("size = %d \n", sizeof(memory));// 64λϵͳ = 8
    free(memory);
    memory = NULL;//�ÿ��Ǹ���ϰ��
}