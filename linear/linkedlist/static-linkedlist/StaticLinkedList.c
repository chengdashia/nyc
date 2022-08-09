//
// Created by Serendipity on 2022/8/9.
//
#include <stdio.h>
#define MaxSize 10        //静态链表的最大长度
#define ElemType int
typedef struct {         //静态链表结构类型的定义
    ElemType data;      //存储数据元素
    int next;           //下一个元素的数组下标
}SLinkList[MaxSize];
