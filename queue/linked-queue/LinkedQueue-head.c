//
// Created by Serendipity on 2022/8/14.
//
/**
 * 带头结点
 */
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>

#define ElemType int
typedef struct LinkNode{     //链式队列结点
    ElemType data;
    struct LinkNode *next;
}LinkNode;
typedef struct {                    //链式队列
    LinkNode *front,*rear;         //队列的对头和对尾指针
}LinkQueue;
//初始化队列(带头结点)
void InitQueue(LinkQueue *Q){
    //初始化时，front、rear都指向头结点
    Q->front = Q->rear = (LinkNode *)malloc(sizeof(LinkNode));
    Q->front->next = NULL;
}
/**
* 判断队列是否为空
*/
bool IsEmpty(LinkQueue Q){
    if(Q.front == Q.rear)
        return true;
    return false;
}
/**
* 新元素入队
*/
bool EnQueue(LinkQueue *Q,ElemType x){
    LinkNode *s = (LinkNode *) malloc(sizeof(LinkNode));
    if(s == NULL)
        return false;
    s->data = x;
    s->next = NULL;
    Q->rear->next = s;         //新结点插入到rear之后
    Q->rear = s;              //修改队尾指针
    return true;
}
/**
 * 对头元素出列
 */
bool DeQueue(LinkQueue *Q,ElemType *x){
    if(IsEmpty(*Q))             //对列为空
        return false;
    LinkNode *p = Q->front->next;  //队头指向的第一个元素
    *x = p->data;                  //用变量x返回队头元素
    Q->front->next = p->next;      //修改队头指向的第一个元素。即第二个元素
    if(Q->rear == p)               // 此次是最后一个结点出队
        Q->rear = Q->front;        //修改rear 指针
    free(p);               //释放结点空间
    return true;

}
