/*
 ============================================================================
 
 Author      : Ztiany
 Description : ʹ��struct�Զ������ݽṹ

 ============================================================================
 */

#include <iostream>
#include "struct_type.h"
#include <cstring>

using namespace std;


static void test_sales_data() {

    Sales_data data1;//����һ��Sales_data

    double price;//��ĵ���

    cin >> data1.bookNo >> data1.units_sold >> price;//���뵥��

    data1.revenue = data1.units_sold * price;

    cout << "bookNo = " << data1.bookNo << ", units_sold = " << data1.units_sold << ", revenue=" << data1.revenue;
}

static void test_Books() {
    Books Book1, Book2;

    // Book1 ����
    strcpy(Book1.title, "C++ �̳�");//�����޷�ֱ�Ӹ�ֵ��ֻ�ܿ���
    strcpy(Book1.author, "Runoob");
    strcpy(Book1.subject, "�������");
    Book1.book_id = 12345;

    // Book2 ����
    strcpy(Book2.title, "CSS �̳�");
    strcpy(Book2.author, "Runoob");
    strcpy(Book2.subject, "ǰ�˼���");
    Book2.book_id = 12346;

    // ��� Book1 ��Ϣ
    cout << "��һ������� : " << Book1.title << endl;
    cout << "��һ�������� : " << Book1.author << endl;
    cout << "��һ������Ŀ : " << Book1.subject << endl;
    cout << "��һ���� ID : " << Book1.book_id << endl;

    // ��� Book2 ��Ϣ
    cout << "�ڶ�������� : " << Book2.title << endl;
    cout << "�ڶ��������� : " << Book2.author << endl;
    cout << "�ڶ�������Ŀ : " << Book2.subject << endl;
    cout << "�ڶ����� ID : " << Book2.book_id << endl;

}


int main() {
    test_sales_data();
    test_Books();
    return EXIT_SUCCESS;
}