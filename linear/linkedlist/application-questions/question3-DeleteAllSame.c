//
// Created by Serendipity on 2022/8/10.
//
/**
 * 对长度为n的顺序表L，编写一个时间复杂度为O(n),空间复杂度为O(1)的算法，
 * 该算法删除线性表中所有值为x的数据元素
 */
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

#define ElemType int
#define InitSize 10
typedef struct {
    ElemType *data;
    int length;
    int MaxSize;
}SqList;
//初始化一个顺序表
void InitList(SqList *L){
    L->data = (ElemType*)malloc(sizeof(ElemType) * InitSize);
    L->length = 0;
    L->MaxSize = InitSize;
    for (int i = 0; i < L->MaxSize; ++i) {
        L->data[i] = 0;
    }
}
//插入
bool ListInsert(SqList *L,int i,ElemType e){
    if(i < 1 || i > L->length + 1)
        return false;
    if(L->length >= L->MaxSize)
        return false;
    for (int j = L->length - 1; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;
    L->length++;
    return true;
}
//删除顺序表中的和x相同的元素
bool DeleteAllSameX(SqList *L,ElemType x){
    printf("顺序表的长度为：%d\n",L->length);
    int k = 0;
    for (int i = 0; i < L->length; ++i) {
        if(L->data[i] != x){
            L->data[k++] = L->data[i];
        }
    }
    L->length = k;
    return true;
}
//查看顺序表的数据
void ListPrint(SqList *L){
    for (int i = 0; i < L->length; ++i) {
        printf("第%d个位置,元素为:%d\n",i,L->data[i]);
    }
}
int main(){
    SqList L;
    InitList(&L);
    //插入模拟数据
    ListInsert(&L,1,2);
    ListInsert(&L,2,5);
    ListInsert(&L,3,6);
    ListInsert(&L,4,1);
    ListInsert(&L,5,6);
    ListInsert(&L,6,6);
    ListPrint(&L);
    //删除所有的x元素
    printf("删除所有的x元素\n============\n");
    ElemType x = 6;
    DeleteAllSameX(&L,x);
    ListPrint(&L);
}