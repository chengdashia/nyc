//
// Created by Serendipity on 2022/8/13.
//
#include <stdio.h>
#include <stdbool.h>
#include <malloc.h>

#define ElemType int
typedef struct StackNode{
    ElemType data;                        //数据域
    struct StackNode *next;              //指针域
}StackNode,*LiStack;                            //栈类型定义
//栈初始化
bool InitLiStack(LiStack *S){
    S = NULL ;
    return true;
}
//判空
bool StackEmpty(LiStack S){
    return S->next == NULL;
}
//入栈 push
bool Push(LiStack *S,ElemType x){
    StackNode *s = (StackNode *)malloc(sizeof(StackNode));
    if(s == NULL)
        return false;     //申请结点空间失败,插入失败，函数返回false
    s->next = *S;
    (*S) = s;
    return true;
}
//出栈 pop
bool pop(LiStack *S,ElemType *x){
    if(S == NULL)         //栈空
        return false;
    *x = (*S)->data;  //将栈顶元素赋给x
    StackNode *p = *S;
    *S = p->next;
    return true;
}
int main(){

}