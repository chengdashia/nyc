//
// Created by Serendipity on 2022/8/10.
//
/**
* 从有序顺序表中删除所有其值重复的元素，使表中所有元素的值均不同
*/
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>
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
    if(L->length >= InitSize)
        return false;
    for (int j = L->length; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;
    L->length++;
    return true;
}
//从有序顺序表中删除所有其值重复的元素，使表中所有元素的值均不同
bool GoHeavy(SqList *L){
    if(L->length == 0)
        return false;
    int i,j;
    for (i = 0,j = 1; j < L->length; j++) {
        if(L->data[i] != L->data[j])
            L->data[++i] = L->data[j];
    }
    L->length = i + 1;
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
    for (int i = 1; i < 7; ++i) {
        ListInsert(&L,i,i+1);
    }
    ListInsert(&L,2,2);
    ListInsert(&L,2,2);
    ListInsert(&L,2,2);
    ListInsert(&L,2,2);
    ListPrint(&L);
    //去重
    printf("去重后元素\n============\n");
    GoHeavy(&L);
    ListPrint(&L);
}