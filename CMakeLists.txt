cmake_minimum_required(VERSION 3.23)
project(project C)

set(CMAKE_C_STANDARD 99)


add_executable(seqList-static linear/sequencetable/seqList-static.c)
add_executable(seqList-dynamic linear/sequencetable/seqList-dynamic.c)

add_executable(SingleLinkedList linear/linkedlist/SingleLinkedList.c)
add_executable(SingleLinkedList-head linear/linkedlist/SingleLinkedList-head.c)
add_executable(DoubleLinkedList linear/linkedlist/doublelinkedlist/DoubleLinkedList-head.c)