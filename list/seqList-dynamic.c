//
// Created by Serendipity on 2022/8/4.
//
#include <stdio.h>
#include <malloc.h>

#define InitSize 10
#define ElemType int
typedef struct {
    ElemType *data;     //指示动态分配数组的指针
    int MaxSize;        //顺序表的最大容量
    int length;         // 顺序表的当前长度
}SeqList;              //顺序表的类型定义（动态分配方式）
//基本操作——初始化一个顺序表
void InitList(SeqList *L){
    //malloc 函数返回一个指针，需要强制转型为你定义的数据元素类型指针
    //InitSize  malloc 函数的参数，指明要分配多大的连续内存空间
    L->data = (ElemType *)malloc(sizeof(ElemType) * InitSize);
    L->length = 0;    //顺序表的初始化长度为0
    L->MaxSize = InitSize;
}
//增加动态数组的长度
void IncreaseSize(SeqList *L,int len){
    int *p = L->data;
    L->data = (ElemType *) malloc((L->MaxSize + len) * sizeof(ElemType));
    for (int i = 0; i < L->length; ++i) {
        L->data[i] = p[i];        //将数据复制到新区域
    }
    L->MaxSize = L->MaxSize + len;  //顺序表最大长度增加len
    free(p);               //释放原来的内容空间
}
int main(){
    SeqList L;       //声明一个顺序表
    InitList(&L);  //初始化顺序表
    L.data[1] = 1;
    for (int i = 0; i < L.MaxSize; ++i) {
        printf("%d\n",L.data[i]);
    }

}