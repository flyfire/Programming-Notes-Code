/*
 ============================================================================
 
 Author      : Ztiany
 Description : 运算符重载

 ============================================================================
 */
#include "oo.h"
#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

int main() {

    Sales_item sales_item("cpp primer");
    sales_item.setRevenue(10000);
    sales_item.setUnits_sold(21);
    cout << sales_item << endl;

    int i = -42;
    absInt absObj;
    unsigned int ui = (unsigned int) absObj(i);
    cout << ui << endl;

    plus<int> intAdd;
    negate<int> intNegate;
    int sum = intAdd(10, 20);
    cout << "sum = " << sum << endl;
    sum = intAdd(10, intNegate(10));
    cout << "sum = " << sum << endl;

    return EXIT_SUCCESS;
}



