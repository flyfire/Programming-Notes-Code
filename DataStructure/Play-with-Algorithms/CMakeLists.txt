cmake_minimum_required(VERSION 3.8)
project(Play-with-Algorithms)

set(CMAKE_CXX_STANDARD 11)

#dirs
aux_source_directory(sort-basic SORT_BASE)
aux_source_directory(heap HEAP)
aux_source_directory(bst BST)
aux_source_directory(tools TOOLS)
#main
set(SOURCE_MAIN BSMain.cpp)
#build
add_executable(Play-with-Algorithms ${SOURCE_MAIN} ${SORT_BASE} ${HEAP} ${BST} ${TOOLS})