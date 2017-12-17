/*
 ============================================================================
 
 Author      : Ztiany
 Description : 友元

 ============================================================================
 */

#ifndef C_BASIC_FRIEND_CLASS_H
#define C_BASIC_FRIEND_CLASS_H


#include <iostream>

class Box {
    double width;
public:

    friend void printWidth(Box box);

    friend class Friend;

    void setWidth(double wid);
};

// 成员函数定义
void Box::setWidth(double wid) {
    width = wid;
}

// 请注意：printWidth() 不是任何类的成员函数
void printWidth(Box box) {
    /* 因为 printWidth() 是 Box 的友元，它可以直接访问该类的任何成员 */
    std::cout << "Width of box : " << box.width << std::endl;
}

class Friend {
public:
    double getBoxWidth(Box box) {
        return box.width;
    }
};

#endif //C_BASIC_FRIEND_CLASS_H
