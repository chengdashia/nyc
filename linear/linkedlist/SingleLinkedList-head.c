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
    //分配一个头指针
    *L = (LNode *) (LNode *) malloc(sizeof(LNode));
    if(L == NULL)     //头结点之后暂时还没有节点
        return false;
    (*L)->next = NULL;   //头结点之后暂时没有节点
    return true;
}
//判断单链表是否为空
bool Empty(LinkList *L){
    return (*L)->next == NULL;
}
//后插操作；在p结点之后插入元素e
bool InsertNextNode(LNode *p,ElemType e){
    if(p == NULL)
        return false;
    LNode *s = (LNode *) malloc(sizeof(LNode));
    if(s == NULL)          //内存分配失败
        return false;
    s->data = e;      //用结点s保存数据元素e
    s->next = p->next;
    p->next = s;      //将结点s连到p之后
    return true;
}
//在第i个位置插入元素 e
bool  ListInsert(LinkList *L,int i ,ElemType e){
    if(i < 1)
        return false;
    LNode *p; //指针p指向当前扫描的结点
    p = *L;  //L指向头结点，头结点是第0个结点(不存数据)
    int j = 0; //当前p指向的第几个节点
    while (p != NULL && j < i - 1){ //循环找到 第i- 1个结点
        p = p->next;
        j++;
    }
    InsertNextNode(*L,e);

}
//前插操作:在p结点之前插入元素e
bool InsertPriorNode(LNode *p,ElemType e){
    if(p == NULL)
        return false;
    LNode *s = (LNode *) malloc(sizeof(LNode));
    if(s == NULL)          //内存分配失败
        return false;
    s->next = p->next;
    p->next = s;          //新结点s连到p之后
    s->data = p->data;   //将p中元素复制到s中
    p->data = e;       //p中元素覆盖为e
    return true;
}
//前插操作:在p结点之前插入结点
bool NodeInsertPriorNode(LNode *p,LNode *s){
    if(p == NULL)
        return false;
    s->next = p->next;
    p->next = s;          //新结点s连到p之后
    ElemType temp = p->data;   //交换数据部分
    p->data = s->data;       //p中元素覆盖为e
    s->data = temp;   //将p中元素复制到s中

    return true;
}
bool ListDelete(LinkList *L,int i,ElemType *e){
    if(i < 1)
        return false;
    LNode *p; //指针p指向当前扫描的结点
    p = *L;  //L指向头结点，头结点是第0个结点(不存数据)
    int j = 0; //当前p指向的第几个节点
    while (p != NULL && j < i - 1){ //循环找到 第i- 1个结点
        p = p->next;
        j++;
    }
    if(p == NULL)    //i值不合法
        return false;
    if(p->next == NULL)  //第i-1个结点之后已无其他结点
        return false;
    LNode *q = p->next;         //令q指向被删除结点
    *e = q->data;         //用e接收返回元素的值
    p->next = q->next;   //用q结点从链中断开
    free(q);   // 释放结点的存储空间
    return true;

}
//删除指定结点p
bool DeleteNode(LNode *p){
     if(p == NULL)
         return false;
    LNode *q = p->next;         //令q指向*p的后继结点
    p->data = p->next->data;    //和后继结点交换数据域
    p->next = q->next;          //将*q结点从链中断开
    free(q);           //释放后继结点的存储空间
    return true;
}
//按位查找，返回第i个元素
LNode * GetElem(LinkList *L,int i){
    if(i < 0)
        return false;
    LNode *p; //指针p指向当前扫描的结点
    p = *L;  //L指向头结点，头结点是第0个结点(不存数据)
    int j = 0; //当前p指向的第几个节点
    while (p != NULL && j < i ){ //循环找到 第i个结点
        p = p->next;
        j++;
    }
    return p;
}
//按值查找，找到数据域==e 的结点
LNode * LocateElem(LinkList *L,ElemType e){
    LNode *p = (*L)->next;
    //从第1个结点开始查找数据域为e的结点
    while (p != NULL && p->data != e){
        p = p->next;
    }
    return p;           //找到后返回该结点指针，否则返回NULL
}
//求表的长度
int length(LinkList *L){
    int len = 0;
    LNode *p = *L;
    while (p->next != NULL){
        p = p->next;
        len ++;
    }
    return len;
}
int main(){
    //声明一个指向单链表的指针
    LinkList L;
    //初始化一个空表
    InitList(&L);
}