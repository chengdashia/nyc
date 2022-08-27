//
// Created by Serendipity on 2022/8/22.
//
/**
 * 递归实现在单链表L中删除值为x的结点
 */
#include <stdio.h>
#include <stdbool.h>
#include <malloc.h>

#define ElemType int
typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode,*LinkList;
//初始化一个空的单链表
bool InitList(LinkList *L){
   L = NULL;      //空表，暂时还没有任何节点（防止脏数据）
    return true;
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
/**
 * 递归删除单链表L中元素为x 的
 * @param L
 * @param e
 */
void Del_x_3(LinkList *L,ElemType e){
    LNode *p;                   //p指向待删除结点
    if(L == NULL)               //递归出口
        return;
    if((*L)->data == e){        //若L所指向的结点的值为e
        p = *L;                 //删除*L,并让L指向下一个结点
        (*L) = (*L)->next;
        free(p);
        Del_x_3(L,e);          //递归调用
    } else{                    //若L所指向的结点的值不为e
        (*L) = (*L)->next;
        Del_x_3(L,e);           //递归调用
    }
}
int main(){

}