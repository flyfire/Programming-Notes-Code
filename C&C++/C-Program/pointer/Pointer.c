/*
 ============================================================================

 Author      : Ztiany
 Description : ����ָ����ʹ��ָ��

 ============================================================================
 */

#include <stdio.h>

static void pointerSample();

static void errorSample();

static void findMax();

static void swapMax();

static void swap(int *, int *);

static void constPointer();

static void pointerSize();

int main() {
    pointerSample();
    pointerSize();
    errorSample();
    findMax();
    swapMax();
    constPointer();
    return 0;
}

static void constPointer() {
    int a = 4;
    //��ʱ�޷��޸�pa��ֵҲ�޷��޸�paָ���ֵ
    const const int *ccpa = &a;
    //��ʱ�޷��޸�pa��ֵ
    const int *cpa = &a;
}

static void swapMax() {
    int a, b, *p1, *p2;
    //https://stackoverflow.com/questions/3420629/what-is-the-difference-between-sscanf-or-atoi-to-convert-a-string-to-an-integer
    scanf("%d %d", &a, &b);
    p1 = &a;
    p2 = &b;
    if (a < b) {
        swap(p1, p2);
    }
    printf("�ϴ����Ϊ %d, ��С����Ϊ %d\n", a, b);
}

static void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
    /*
     ��Զ��Ҫ�����������ܻ�������벻���Ľ����
       int *tem;//����һ��ָ���û�и�ֵ����ʱ���ָ���ָ��һ��δ֪�ĵ�ַ
       *tem = *a;//�������ʱ�� *tem��ֵ���ͻ�ı��Ǹ�δ֪��ַ��ֵ
    */
}


static void findMax() {
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

static void errorSample() {
    /*
       ����ᵼ�³����쳣����Ϊ��ַ10�϶�������
       int * a = 10;
       printf("%d", *a);
     */
    printf("int * a = 10; �Ǵ���ģ����Ϸ��ġ�\n");
}

static void pointerSize() {
    int *pi[10];
    char *pc[10];
    float *pf[10];
    double *pd[10];

    struct Student {
        int age;
        char *name;
        char *address;
        int id;
    };

    struct Student student = {1, "Ztiany", "China", 001};

    printf("int *p[10]��size = %d\n", sizeof(pi));//8*10=80
    printf("char *p[10]��size = %d\n", sizeof(pc));//8*10=80
    printf("float *p[10]��size = %d\n", sizeof(pf));//8*10=80
    printf("double *p[10]��size = %d\n", sizeof(pd));//8*10=80
    printf("student��size = %d\n", sizeof(student));//32
    printf("&student��size = %d\n", sizeof(&student));//8
}

static void pointerSample() {
    int a = 4;
    int *pa = &a;//ȡa�ĵ�ַ��ֵ��ָ��aP
    printf("ָ�� aP �ĵ�ַ= %p\n", pa);
    printf("ָ�� aP ��ֵ = %d\n", *pa);
    *pa = 7;//ȡaP�ĵ�ַ����ֵ��Ҳ���Ǹ�a��ֵ
    printf("���� a = %d\n", a);
}
