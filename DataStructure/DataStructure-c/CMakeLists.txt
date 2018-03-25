cmake_minimum_required(VERSION 3.7)
project(DataStructure-c)

set(CMAKE_C_STANDARD 99)


set(SOURCE_FILES
        DynamicArray.c
        LinkedList.c
        CompaniesLinkedList.c
        SeqStack.c
        LinkedStack.c
        SeqQueue.c
        CircleLinkedList.c
        Main.c
        )

add_executable(DataStructure-c ${SOURCE_FILES})