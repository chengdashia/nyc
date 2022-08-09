//
// Created by Serendipity on 2022/8/9.
//
#include <stdio.h>
#define MaxSize 10        //静态链表的最大长度
#define ElemType int
struct Node{
    ElemType data;       //存储数据元素
    int next;            //下一个元素的数组下标
};
typedef struct {         //静态链表结构类型的定义
    ElemType data;      //存储数据元素
    int next;           //下一个元素的数组下标
}SLinkList[MaxSize];
void main(){
    struct Node x;
    printf("sizeX = %d\n",sizeof(x));

    struct Node a[MaxSize];
    printf("sizeA = %d\n",sizeof(a));

    /**结论：
     *  SLinkedList b --- 相当于定义了一个长度为MaxSize 的 Node型数组
     */
    SLinkList b;
    printf("sizeB = %d\n",sizeof(b));
}