/*
 ============================================================================
 Author      : Ztiany
 Description : C���Ե���������
 ============================================================================
 */

#include <stdio.h>//����ͷ�ļ�����Ӧ�ó���ӿڣ�����Java�ĵ���
#include <stdbool.h>//֧�ֲ������͵�ͷ�ļ�

#define A 1.3//���峣��

const int days[3] = {1, 2, 3};//const���峣���������Զ�������

void dataType();//�������д��main���棬��Ҫ��main����ǰ���������������
void memoryAddress();

//C����һ���Ǵ���������ʼִ�еģ�main�����ķ���ֵΪint����.
int main() {
    dataType();//�����������������´�С
    memoryAddress();//��ȡ�ڴ��ַ
    return 1;
}


void dataType() {

    //C�����е���������
    char c = 'c';
    short hd = 1233;
    int d = 12312312;
    long sld = 432432;
    long long ld = 232423423;
    unsigned int ud = 323;
    unsigned long uld = 3333;
    float f = 1212.132121F;
    double lf = 232.222222222;
    bool flag = false;    //�������ͣ�c99���
    _Bool b1 = 1;//c99���
    _Bool b2 = 0;//c99���


    //��ӡ����
    printf("%c\n", c);
    printf("%ld\n", sld);
    printf("%ld\n", uld);
    printf("%hd\n", hd);
    printf("%d\n", d);
    printf("%d\n", ud);
    printf("%lld\n", ld);
    printf("%d\n", flag);
    printf("%d\n", b1);
    printf("%d\n", b2);
    printf("%f\n", f);
    printf("%lf\n", lf);

    //��ӡ�������͵ĳ���
    printf("�ַ��ĳ�����:%zd\n", sizeof(char));
    printf("short�ĳ�����:%zd\n", sizeof(short));
    printf("int�ĳ�����:%zd\n", sizeof(int));
    printf("long�ĳ�����:%zd\n", sizeof(long));
    printf("long long�ĳ�����:%zd\n", sizeof(long long));
    printf("float�ĳ�����:%zd\n", sizeof(float));
    printf("double�ĳ�����:%zd\n", sizeof(double));
    printf("����A=%f", A);
}


//��ȡ���ӡ�ڴ��ַ
void memoryAddress() {
    int cc = 3;
    printf("%p\n", &cc);
}


