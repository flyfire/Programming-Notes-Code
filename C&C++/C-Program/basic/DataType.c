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

//C����һ���Ǵ���������ʼִ�еġ�main�����ķ���ֵΪint����.
int main() {
    dataType();//�����������������´�С
    memoryAddress();//��ȡ�ڴ��ַ
    return 1;
}

/*
 * ������
 */
void dataType() {

    char c = 'c';
    short hd = 1233;
    int d = 12312312;
    long long ld = 232423423;
    long sld = 432432;
    float f = 1212.132121F;
    double lf = 232.222222222;
    bool flag = false;    //�������ͣ�c99���
    _Bool b1 = 1;
    _Bool b2 = 0;

    //��ӡ����
    printf("%c\n", c);
    printf("%ld\n", sld);
    printf("%hd\n", hd);
    printf("%d\n", d);
    printf("%lld\n", ld);
    printf("%f\n", f);
    printf("%lf\n", lf);

    printf("�ַ��ĳ�����:%d\n", sizeof(char));
    printf("short�ĳ�����:%d\n", sizeof(short));
    printf("int�ĳ�����:%d\n", sizeof(int));
    printf("long�ĳ�����:%d\n", sizeof(long));
    printf("long long�ĳ�����:%d\n", sizeof(long long));
    printf("float�ĳ�����:%d\n", sizeof(float));
    printf("double�ĳ�����:%d\n", sizeof(double));

    printf("����A=%f", A);
}


//��ȡ���ӡ�ڴ��ַ
void memoryAddress() {
    int cc = 3;
    printf("%p\n", &cc);
}


