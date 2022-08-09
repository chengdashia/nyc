//
// Created by Serendipity on 2022/8/7.
//
#include<stdio.h>
#include <stdbool.h>
#include <malloc.h>

#define Element int
typedef struct LNode{
    Element data;
    struct LNode *next;
}LNode,*LinkedList;
//初始化双链表
bool InitDLinkedList(LinkedList *L){
    *L = (LNode *)malloc(sizeof(LNode));  //分配一个头结点
    if(L == NULL){
        return false;          //内存不足，分配失败
    }
    (*L)->next = *L;        // 头结点next指向头结点
    return true;
}
//判断双链表是否为空（带头结点）
bool Empty(LinkedList *L){
    if((*L)->next == *L){
        return true;
    }
    return false;
}
//判断结点p是否为循环单链表的表尾结点
bool isTail(LinkedList *L,LNode *p){
    if(p->next == *L){
        return true;
    }
    return false;
}

int main(){
    //初始化双链表
    LinkedList L;
    InitDLinkedList(&L);
}