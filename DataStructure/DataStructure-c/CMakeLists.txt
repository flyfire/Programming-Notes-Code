cmake_minimum_required(VERSION 3.7)
project(DataStructure-c)

set(CMAKE_C_STANDARD 99)


#[[set(SOURCE_FILES
        linear-list/DynamicArray.c
        linear-list/LinkedList.c
        linear-list/CompaniesLinkedList.c
        linear-list/SeqStack.c
        linear-list/LinkedStack.c
        linear-list/SeqQueue.c
        linear-list/CircleLinkedList.c
        linear-list/Main.c
        )]]

set(SOURCE_FILES
        tree/
        )

add_executable(DataStructure-c ${SOURCE_FILES})