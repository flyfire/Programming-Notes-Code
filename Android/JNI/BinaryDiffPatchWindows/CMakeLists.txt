cmake_minimum_required(VERSION 3.8)
project(BinaryDiffPatch_Windows)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES library.cpp library.h)
add_library(BinaryDiffPatch SHARED ${SOURCE_FILES})