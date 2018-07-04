cmake_minimum_required(VERSION 3.8)
project(Play-with-Algorithms)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES sort/SortTestHelper.h sort/SortMain.cpp)
add_executable(Play-with-Algorithms ${SOURCE_FILES})