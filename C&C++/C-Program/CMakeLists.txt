cmake_minimum_required(VERSION 3.6)
project(C_Program)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES Strings/Strings.c)
add_executable(C_Program ${SOURCE_FILES})