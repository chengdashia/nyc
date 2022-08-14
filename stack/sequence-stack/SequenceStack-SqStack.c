//
// Created by Serendipity on 2022/8/13.
//
#include <stdio.h>
#include <stdbool.h>

#define MaxSize 10                //定义栈中的元素的最大个数
#define ElemType int
typedef struct {
    ElemType data[MaxSize];       //静态数组存放栈中元素
    int top;                      //栈顶指针
}SqStack;
//初始化栈
void InitStack(SqStack *S){
    S->top = -1;                  //初始化栈顶指针
}
//判断栈空
bool StackEmpty(SqStack *S){
    if(S->top == -1)      //栈为空
        return true;
    else                  //栈不为空
        return false;
}
//判断栈满
bool StackFull(SqStack *S){
    if(S->top + 1 == MaxSize)      //栈满
        return true;
    else                  //栈不为空
        return false;
}
//新元素入栈
bool Push(SqStack *S,ElemType x){
    if(S->top == MaxSize - 1)  //栈满，报错
        return false;
    S->data[++S->top] = x;    //指针先加1，然后新元素入栈
    return true;
}
//出栈操作
bool Pop(SqStack *S,ElemType *x){
    if(StackEmpty(S))          //栈为空，报错
        return false;
    *x = S->data[S->top--]; //栈顶元素先出栈，指针再减一
    return true;
}
//读栈顶元素
bool GetTop(SqStack *S,ElemType *x){
    if(StackEmpty(S))          //栈为空，报错
        return false;
    *x = S->data[S->top]; //x记录栈顶元素
    return true;
}
int main(){
    SqStack S;         //声明一个顺序栈（分配空间）
    InitStack(&S);
}