//
// Created by Serendipity on 2022/8/14.
//
#include <stdio.h>
#include <stdbool.h>
#define MaxSize 10                //定义栈中的元素的最大个数
#define ElemType int
typedef struct {
    ElemType data[MaxSize];       //静态数组存放栈中元素
    int front,rear;               //对头指针和对尾指针
    /**
     * 每次删除操作成功时，都令tag = 0;
     * 每次插入操作成功时，都令tag = 1;
     */
    int tag;                    //最近进行的是删除/插入
}SqQueue;
//初始化栈
void InitStack(SqQueue *Q){
    Q->front = Q->rear = Q->tag = 0;            //初始化时，对头、对尾指针指向0   队列长度为0
}
/**
 * 判断队列是否为空
 * 只有删除操作，才可能导致队满
 */
bool QueueEmpty(SqQueue Q){
    if(Q.front == Q.rear && Q.tag == 0)      //如果对头指针=对尾指针，且最近一次操作是删除
        return true;
    else                  //队不为空
        return false;
}
/**
 * 判断队列已满 ：对尾指针的再下一个位置是对头
 * 只有插入操作，才可能导致队满
 */
bool QueueFull(SqQueue Q){
    if(Q.front == Q.rear && Q.tag == 1)      //如果对头指针=对尾指针，且最近一次操作是插入
        return true;
    else                  //队列不满
        return false;
}
//新元素入队
bool Push(SqQueue *Q,ElemType x){
    if(QueueFull(*Q))
        return false;
    Q->data[Q->rear] = x;                // 新元素插入对尾
    /**
     * 模运算是将无限的整数域映射到有限的整数集合
     * 用模运算将存储空间在逻辑上变成了“环状”
     */
    Q->rear = (Q->rear + 1) % MaxSize;   //对尾指针加1取模
    Q->tag = 1;  //插入成功
    return true;
}
//出队操作, 删除一个对头元素，并用x返回
bool DeQueue(SqQueue *Q,ElemType *x){
    if(QueueEmpty(*Q))          //队为空，报错
        return false;
    *x = Q->data[Q->front];              //对头元素先出栈
    Q->front = (Q->front + 1) % MaxSize; //对头指针后移
    Q->tag = 0;      //删除成功
    return true;
}
//读对头元素，用x返回
bool GetHead(SqQueue Q,ElemType *x){
    if(QueueEmpty(Q))          //队为空，报错
        return false;
    *x = Q.data[Q.front]; //x记录栈顶元素
    return true;
}
//获取队列元素个数
int GetQueueLength(SqQueue Q){
    return (Q.rear + MaxSize - Q.front) % MaxSize;
}
int main(){
    SqQueue Q;         //声明一个队列（分配空间）
    InitStack(&Q);
}