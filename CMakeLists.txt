cmake_minimum_required(VERSION 3.23)
project(project C)

set(CMAKE_C_STANDARD 99)


add_executable(seqList-static linear/sequencetable/seqList-static.c)
add_executable(seqList-dynamic linear/sequencetable/seqList-dynamic.c)

add_executable(SingleLinkedList linear/linkedlist/SingleLinkedList.c)
add_executable(SingleLinkedList-head linear/linkedlist/SingleLinkedList-head.c)
add_executable(DoubleLinkedList linear/linkedlist/doublelinkedlist/DoubleLinkedList-head.c)
add_executable(cycleLinkedList linear/linkedlist/doublelinkedlist/cycleSingleLinkedList.c)
add_executable(cycleDoubleLinkedList linear/linkedlist/doublelinkedlist/cycleDoubleLinkedList.c)
add_executable(StaticLinkedList linear/linkedlist/static-linkedlist/StaticLinkedList.c)
add_executable(StaticLinkedList-guess linear/linkedlist/static-linkedlist/StaticLinkedList-guess.c)
add_executable(question13 linear/linkedlist/application-questions/question13.c)
add_executable(question2 linear/linkedlist/application-questions/question2.c)