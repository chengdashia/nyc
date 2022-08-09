//
// Created by Serendipity on 2022/8/9.
//
/**
 * 设计一个高效的算法，将顺序表L的所有元素逆置
 */
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>
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
//动态扩展顺序表的长度
void IncreaseSize(SeqList *L,int len){
    int *temp = L->data;
    L->data = (ElemType *) malloc(sizeof(ElemType) * (L->MaxSize + len));
    for (int i = 0; i < L->length; ++i) {
        L->data[i] = temp[i];
    }
    L->MaxSize = L->MaxSize + len;
    free(temp);
}
//插入
bool ListInsert(SeqList *L,int i,int e){
    if(i < 1 || i > L->length + 1)         //判断i的范围是否有效
        return false;
    if(L->length >= InitSize)              //当前的存储空间已满，不能插入
        return false;
    for (int j = L->length - 1; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;                //在位置i处放入e
    return true;

}
//查看顺序表的数据
void ListPrint(SeqList *L){
    for (int i = 0; i < L->MaxSize; ++i) {
        printf("第%d个位置,元素为:%d\n",i,L->data[i]);
    }
}
//将顺序表L的所有元素逆置
void InverseList(SeqList *L){
    ElemType temp;
    for (int i = 0; i < L->length / 2; ++i) {
        temp = L->data[i];
        L->data[i] = L->data[L->length - i - 1];
        L->data[L->length - i - 1] = temp;
    }
}
int main(){
    SeqList L;
    InitList(&L);
    //插入
    for (int i = 1; i <= 10; ++i) {
        ListInsert(&L,i,i+1);
    }
    ListPrint(&L);

    printf("===============逆置后==========\n");
    InverseList(&L);
    ListPrint(&L);
}