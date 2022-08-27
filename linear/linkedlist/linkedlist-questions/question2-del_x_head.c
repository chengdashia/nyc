//
// Created by Serendipity on 2022/8/22.
//
#include <stdio.h>
#include <malloc.h>
#define ElemType int
typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode,*LinkList;
void del_x(LinkList *L,ElemType x){
    LNode *p = (*L)->next,*pre = (*L),*q;         //置p和pre的初始值
    while (p != NULL){
        if(p->data == x){
            q = p;                               //q指向该结点
            p = q->next;
            pre->next = p;                       //释放*q结点的空间
            free(q);                     //释放*q结点的空间
        } else{                                  //
            pre = p;                             //否则，pre和p同步后移
            p = p->next;
        }
    }
}
int main(){

}