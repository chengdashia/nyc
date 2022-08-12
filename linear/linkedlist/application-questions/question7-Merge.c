//
// Created by Serendipity on 2022/8/11.
//
/**
* 将两个游学顺序表合并为一个新的有序顺序表，并由函数返回结果顺序表
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
// 将两个有序顺序表合并为一个新的有序顺序表，并由函数返回结果顺序表
bool Merge(SqList L1,SqList L2,SqList *L){
    if(L1.length == 0){        //如有L1的长度为0则合并后的顺序表只是L2
        L->data = L2.data;
        L->length = L2.length;
        return true;
    }
    if(L2.length == 0){         //如有L2的长度为0则合并后的顺序表只是L1
        L->data = L1.data;
        L->length = L1.length;
        return true;
    }
    if(L1.length + L2.length > InitSize)    //如果两个有序表的长度大于顺序表的最大长度，则不能合并
        return false;
    int i = 0,j = 0,k = 0;
    while (i < L1.length && j < L2.length){
        if(L1.data[i] < L2.data[j])
            L->data[k++] = L1.data[i++];
        else
            L->data[k++] = L2.data[j++];
    }
    while (i < L1.length)
        L->data[k++] = L1.data[i++];
    while (j < L2.length)
        L->data[k++] = L2.data[j++];
    L->length = k;
    return true;
}
//查看顺序表的数据
void ListPrint(SqList L){
    for (int i = 0; i < L.length; ++i) {
        printf("第%d个位置,元素为:%d\n",i,L.data[i]);
    }
}
int main(){
    SqList L1,L2,L;
    InitList(&L1);
    InitList(&L2);
    InitList(&L);
    //插入模拟数据
    for (int i = 1; i < 5; ++i) {
        ListInsert(&L1,i,i+1);
//        ListInsert(&L2,i,i+2);
    }
    printf("\n======L1======\n");
    ListPrint(L1);
    printf("\n======L2======\n");
    ListPrint(L2);
    //去重
    printf("\n======合并后======\n");
    Merge(L1,L2,&L);
    ListPrint(L);


}