//
// Created by Serendipity on 2022/8/5.
//
#include <stdio.h>
#include <stdbool.h>
#include <malloc.h>

#define ElemType int
typedef struct LNode {   //定义单链表节点类型
    ElemType data;       //每个节点存放一个数据元素
    struct LNode *next; //指针指向下一个节点
}LNode,*LinkList;
/**
 * 使用 LinkList    强调这是一个单链表
 * 使用 LNode*      强调这是一个节点
 * @return
 */
//初始化一个空的单链表
bool InitList(LinkList *L){
    L = NULL;                //空表，暂时还没有任何节点（防止脏数据）
    return true;
}
//判断单链表是否为空
bool Empty(LinkList *L){
    return L == NULL;
}
//按位序插入
bool ListInsert(LinkList *L,int i, ElemType e){
    if(i < 1)
        return false;
    if(i == 1){
        LNode *s = (LNode *)malloc(sizeof(LNode));
        s->next = (*L)->next;
        (*L)->next = s;
        s->data = e;
    }
    LNode *p; //指针p指向当前扫描的结点
    p = *L;  //L指向头结点，头结点是第0个结点(不存数据)
    int j = 1; //当前p指向的第几个节点
    while (p != NULL && j < i - 1){ //循环找到 第i- 1个结点
        p = p->next;
        j++;
    }
    if(p == NULL)          //i值不合法
        return false;
    LNode *s = (LNode *) malloc(sizeof(LNode));
    s->data = e;
    s->next = p->next;
    p->next = s;      //将结点s连到p之后
    return true;
}
int main(){
    //声明一个指向单链表的指针
    LinkList L;
    //初始化一个空表
    InitList(&L);
}