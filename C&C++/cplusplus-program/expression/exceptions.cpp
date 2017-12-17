/*
 ============================================================================
 
 Author      : Ztiany
 Description : 异常

 ============================================================================
 */
#include <iostream>
#include <vector>

using namespace std;

static void tryCatch() {

    try {
        //throw用于抛出异常
        throw exception();
    } catch (exception e) {//catch用于抓住异常
        cout << e.what() << endl;
    }

    try {
        throw logic_error("throw error");
    } catch (logic_error logicError) {
        cout << logicError.what() << endl;
    }
}


struct MyException : public exception {
    const char *what() const throw() {
        return "C++ Exception";
    }
};

void customException() {
    try {
        throw MyException();
    }
    catch (MyException &e) {
        std::cout << "MyException caught" << std::endl;
        std::cout << e.what() << std::endl;
    }
    catch (std::exception &e) {
        //其他的错误
    }
}

int main() {
    tryCatch();
    customException();
    return EXIT_SUCCESS;
}