cmake_minimum_required(VERSION 3.6)
project(C_File)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES ErrorHandle.c)
add_executable(C_File ${SOURCE_FILES})