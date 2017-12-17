/*
 ============================================================================
 
 Author      : Ztiany
 Description : 类的继承

 ============================================================================
 */

#include "inheritance.h"
#include <iostream>

using namespace std;

int main() {

    Rectangle Rect;

    Rect.setWidth(5);
    Rect.setHeight(7);

    // 输出对象的面积
    cout << "Total area: " << Rect.getArea() << endl;
}
