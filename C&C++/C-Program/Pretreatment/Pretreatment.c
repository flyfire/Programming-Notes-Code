/*
 ============================================================================

 Author      : Ztiany
 Description : Ԥ����

 ============================================================================
 */

// ============================================================================
//Ԥ���������ļ�
#include <stdio.h>

// ============================================================================
//Ԥ�����궨��
#define EXIT_SUCCESS 0
#define EXIT_FAILURE 1
#define PI 3.14159//����� PI
#undef PI//undef����ȡ���궨��

#define M (n*n+3*n)//����һ���꣬n��˭��ȡ��������ʹ������꣬�����������û��n����ô�������
#define SQUARE(x) ((x)*(x)) // ����һ���������ı��ʽ����ƽ����SQUARE(x) �Ǻ����� ((x)*(x)) �Ǻ����ݣ������ݺ��ڲ�����һ��Ҫ��������������

// \������һ����������һ����ͨ��д��һ�������ϡ����������̫����һ���������ɲ��£���ʹ�ú������������\��
// �ַ����������������#��,�ں궨���У�����Ҫ��һ����Ĳ���ת��Ϊ�ַ�������ʱ����ʹ���ַ����������������#��
#define  MESSAGE_FOR(a, b)  \
    printf(#a " and " #b " Together !\n")

//���ճ���������##�����궨���ڵı��ճ���������##����ϲ������������������ں궨�������������ı�Ǳ��ϲ�Ϊһ����ǡ�
#define TOKEN_PASTER(n) printf ("token" #n " = %d\n", token##n)

//defined() �������Ԥ������ defined ����������ڳ������ʽ�еģ�����ȷ��һ����ʶ���Ƿ��Ѿ�ʹ�� #define �������
// ���ָ���ı�ʶ���Ѷ��壬��ֵΪ�棨���㣩�����ָ���ı�ʶ��δ���壬��ֵΪ�٣��㣩��
#if !defined(MY_NAME)
#define MY_NAME  "Ztiany"
#endif

#define PR(...) printf(__VA_ARGS__)//__VA_ARGS__��һ��Ԥ����꣬��ʾ�ɱ����

#define NEWLINE printf("-------------------------------------------------------\n")

void printPredefinedMacros() {
    //ANSI C ���������ꡣ�ڱ����������ʹ����Щ��
    printf("File :%s\n", __FILE__);
    printf("Date :%s\n", __DATE__);
    printf("Time :%s\n", __TIME__);
    printf("Line :%d\n", __LINE__);
    printf("ANSI :%d\n", __STDC__);
}

int main() {
    //ʹ��M
    int n = 10;
    int m = M;
    printf("n =10 , M =  %d\n", m);

    //ʹ��SQUARE
    int a = SQUARE(10);
    printf("SQUARE(10) = %d\n", a);

    //ʹ��MESSAGE_FOR
    MESSAGE_FOR("Ztiany", "Beautifully Grail");

    //ʹ��TOKEN_PASTER
    int token100 = 10000;
    TOKEN_PASTER(100);

    //ʹ��MY_NAME
    char *name = MY_NAME;
    printf("MY_NAME = %s\n", name);

    //ʹ��NEWLINE
    NEWLINE;

    //ʹ��PR
    PR("Hello PR\n");
    PR("name=%s,age=%d\n","Ztiany",27);

    //��ӡԤ�����
    printPredefinedMacros();
    return EXIT_SUCCESS;
}




