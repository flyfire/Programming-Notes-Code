/*
 ============================================================================

 Author      : Ztiany
 Description : ����ָ����ʹ��ָ��

 ============================================================================
 */
#include <stdio.h>
#include <malloc.h>

void pointerSample();

void errorSample();

void findMax();

void swapMax();


int main() {

    pointerSample();
    errorSample();
    findMax();
    swapMax();
    return 0;
}

void constPointer() {
    int a = 4;
    const const int *pa = &a;//��ʱ�޷��޸�pa��ֵҲ�޷��޸�paָ���ֵ
    const int *pa1 = &a;//��ʱ�޷��޸�pa��ֵ

}

void swapMax() {
    void swap(int *, int *);
    int a, b, *p1, *p2;
    scanf("%d %d", &a, &b);
    p1 = &a;
    p2 = &b;
    if (a < b) {
        swap(p1, p2);
    }
    printf("�ϴ����Ϊ %d, ��С����Ϊ %d\n", a, b);
}

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;

    /*      ��Զ��Ҫ�����������ܻ�������벻���Ľ��
                        int *tem;//����һ��ָ���û�и�ֵ����ʱ���ָ���ָ��һ��δ֪�ĵ�ַ
                        *tem = *a;//�������ʱ�� *tem��ֵ���ͻ�ı��Ǹ�δ֪��ַ��ֵ
    */
}


void findMax() {
    int *p1, *p2, a, b;
    scanf("%d %d", &a, &b);
    p1 = &a;
    p2 = &b;
    if (a < b) {
        p1 = &b;
        p2 = &a;
    }
    printf("�ϴ����Ϊ %d, ��С����Ϊ %d\n", *p1, *p2);
}

void errorSample() {
    /*
            ����ᵼ�³����쳣����Ϊ��ַ10�϶�������
            int * a = 10;
            printf("%d", *a);
     */
    printf("int * a = 10; �Ǵ���ģ����Ϸ��ġ�\n");
}

void pointerSample() {
    int a = 4;
    int *aP = &a;//ȡa�ĵ�ַ��ֵ��ָ��aP
    printf("ָ�� aP = %X\n", aP);
    printf("ָ�� aP ��ֵ = %d\n", *aP);
    *aP = 7;//ȡaP�ĵ�ַ����ֵ��Ҳ���Ǹ�a��ֵ
    printf("���� a = %d\n", a);
}
