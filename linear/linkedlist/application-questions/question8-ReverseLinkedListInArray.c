//
// Created by Serendipity on 2022/8/11.
//
/**
* 已知在一维数组A[m+n] 中依次存放两个线性表.
 * 编写一个函数，将数组中两个顺序表的位置互换
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
//在数组中将两个有序顺序表位置互换
bool Reverse(SqList L1,SqList L2,ElemType nums[]){


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

    ListPrint(L);


}