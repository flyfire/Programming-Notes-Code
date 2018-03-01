/*
 ============================================================================
 
 Author      : Ztiany
 Description : 泛型算法

 ============================================================================
 */

#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <numeric>
#include <vector>

using namespace std;

void findSample() {
    vector<int> vec = {27, 210, 12, 47, 109, 83};
    int val = 83;
    //如果没有找到，则返回vec.cend()
    auto result = find(vec.begin(), vec.end(), val);
    int number = *result;
    cout << "number = " << number << endl;
    cout << "The value " << val << (result == vec.cend() ? " is not present" : " is present") << endl;
}

int main() {
    findSample();
    return EXIT_SUCCESS;
}