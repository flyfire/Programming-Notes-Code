/*
 ============================================================================

 Author      : Ztiany
 Description : stddef.hϵ�к���

 ============================================================================
 */

//stddef .h ͷ�ļ������˸��ֱ������ͺͺꡣ��Щ�����еĴ󲿷�Ҳ����������ͷ�ļ��С�


#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
    void *a = NULL;
    size_t size = sizeof(a);
    size_t wide_char_size = sizeof(wchar_t);
    printf("NULL size = %d\n", size);
    printf("wide_char_size  = %d\n", wide_char_size);

    struct address {
        char name[50];
        char street[50];
        int phone;
    };

    //offsetof:������һ������Ϊ size_t �����ͳ���������һ���ṹ��Ա����ڽṹ��ͷ���ֽ�ƫ��������Ա���� member-designator �����ģ��ṹ���������� type �и����ġ�
    printf("address �ṹ�е� name ƫ�� = %d �ֽڡ�\n", offsetof(struct address, name));
    printf("address �ṹ�е� street ƫ�� = %d �ֽڡ�\n", offsetof(struct address, street));
    printf("address �ṹ�е� phone ƫ�� = %d �ֽڡ�\n", offsetof(struct address, phone));

    return EXIT_SUCCESS;
}