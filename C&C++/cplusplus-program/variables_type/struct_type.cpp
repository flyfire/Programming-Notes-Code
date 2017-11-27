/*
 ============================================================================
 
 Author      : Ztiany
 Description : 使用struct自定义数据结构

 ============================================================================
 */

#include <iostream>
#include "struct_type.h"

using namespace std;

int main() {
    Sales_data data1, data2;

    double price;//书的单价

    cin >> data1.bookNo >> data1.units_sold >> price;
    data1.revenue = data1.units_sold * price;

    cout << "bookNo = " << data1.bookNo << ", units_sold = " << data1.units_sold << ", revenue=" << data1.revenue;

    return EXIT_SUCCESS;
}