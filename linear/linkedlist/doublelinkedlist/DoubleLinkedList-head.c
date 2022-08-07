//
// Created by Serendipity on 2022/8/7.
//
#include<stdio.h>
#include <stdbool.h>
#include <malloc.h>

#define Element int
typedef struct DNode{
    Element data;
    struct DNode *prior,*next;
}DNode,*DLinkedList;
//初始化双链表
bool InitDLinkedList(DLinkedList *L){
    *L = (DNode *)malloc(sizeof(DNode));  //分配一个头结点
    if(L == NULL){
        return false;          //内存不足，分配失败
    }
    (*L)->prior = NULL;         //头结点的prior永远指向NULL
    (*L)->next = NULL;        // 头结点之后暂时还没有结点
    return true;
}
//判断双链表是否为空（带头结点）
bool Empty(DLinkedList *L){
    if((*L)->next == NULL){
        return true;
    }
    return false;
}
//在p结点之后插入s结点
bool InsertNextDNode(DNode *p,DNode *s){
    if(p == NULL || s == NULL) //非法参数
        return false;
    s->next = p->next;    //将结点*s插入到结点*p之后
    if(p->next != NULL)    //如果p结点有后续结点
        p->next->prior = s;
    s->prior = p;
    p->next = s;
}
//删除p结点的后继结点
bool DeleteNextDNode(DNode *p){
    if(p == NULL)
        return  false;
    DNode *q = p->next; //找到p的后继结点
    if(q == NULL)
        return false;           //p没有后继
    p->next = q->next;
    if(q->next != NULL)      //q结点不是最后一个结点
        q->next->prior = p;
    free(q);        //释放结点空间
    return true;
}
//销毁双链表
void DestroyList(DLinkedList *L){
    //循环释放各个数据结点
    while ((*L)->next != NULL){
        DeleteNextDNode(*L);
    }
    free(L);     //释放头结点
    L = NULL;           //头指针指向NULL
}
int main(){
    //初始化双链表
    DLinkedList L;
    InitDLinkedList(&L);
}