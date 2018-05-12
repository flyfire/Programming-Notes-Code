cmake_minimum_required(VERSION 3.7)
project(DataStructure-c)

set(CMAKE_C_STANDARD 99)

#线性表
#[[set(SOURCE_FILES
        linearList/DynamicArray.c
        linearList/LinkedList.c
        linearList/CompaniesLinkedList.c
        linearList/SeqStack.c
        linearList/LinkedStack.c
        linearList/SeqQueue.c
        linearList/CircleLinkedList.c
        linearList/Main.c
        )]]

#二叉树
#[[set(SOURCE_FILES
        linearList/LinkedStack.c
        tree/BinaryTree.c
        )]]

#排序
set(SOURCE_FILES sort/Main.c)

add_executable(DataStructure-c ${SOURCE_FILES})