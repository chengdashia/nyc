//
// Created by Serendipity on 2022/8/13.
//
/**
 * 带头结点
 */
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
    *S = (StackNode *) malloc(sizeof(StackNode));
    if(S == NULL)
        return false;
    (*S)->next = NULL; //设置栈顶指针为空
    return true;
}
//判空
bool StackEmpty(LiStack *S){
    if((*S)->next == NULL)     //如果头结点指向的下一个为NULL
        return true;
    return false;
}
//入栈 push
bool Push(LiStack *S,ElemType x){
    StackNode *s = (StackNode *)malloc(sizeof(StackNode));
    if(s == NULL)
        return false;     //申请结点空间失败,插入失败，函数返回false
    s->next = (*S)->next; //设置新结点的指针城
    s->data = x;         //设置新结点的数据域
    (*S)->next = s;      //设置头结点指针城指向新的栈顶元素
    return true;
}
//出栈 pop
bool pop(LiStack *S,ElemType *x){
    if((*S)->next == NULL)
        return false;
    StackNode *p = (*S)->next;    //见原栈顶数据元素弹出并赋给p
    *x = p->data;                //将栈顶元素赋给x
    (*S)->next = p->next;       //栈顶指针指向链栈中的下一个数据元素
    p->next = NULL;
    free(p);   //释放栈顶元素所占的空间
    return true;
}
//获取栈顶元素
bool GetTopElem(LiStack S,ElemType *x){
    if(S->next){              //如果栈不为空
        *x = S->next->data;   //将栈顶元素赋值给x
        return true;
    }
    return false;
}
//获取栈长
int StackLength(LiStack S){
    int count = 0;
    while (S->next != NULL){
        ++count;
        S = S->next;
    }
    return count;


}
int main(){
    int i,t,x,a[20];
    LiStack S;
    InitLiStack(&S);//初始化栈
    printf("请依次输入5个数,开始入栈：\n");
    for(i=0;i<5;i++)
    {
        scanf("%d",&a[i]);
        Push(&S,a[i]);
        GetTopElem(S,&x);
        if(x!=-1)
        {
            printf("当前栈顶元素为%d\n",x);
        }
    }
    printf("入栈结束\n");
    printf("栈长为%d\n",StackLength(S));
    printf("开始出栈:\n");
    for (i=0;i<5;i++)
    {
        pop(&S,&t);
        printf("%d",t);
    }
    printf("\n");
    printf("出栈后栈长为%d\n",StackLength(S));

}