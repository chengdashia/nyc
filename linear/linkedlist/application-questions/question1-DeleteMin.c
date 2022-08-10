//
// Created by Serendipity on 2022/8/10.
//
/**
* 从顺序表中删除具有最小值的元素(假设唯一)并由函数返回被删元素的值，空出的位置由最后一个元素填补，若顺序表为空，
 * 则显示出错信息并退出运行
*/
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

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
}
//插入
bool ListInsert(SqList *L,int i,ElemType e){
    if(i < 1 || i > L->length + 1)
        return false;
    if(L->length >= L->MaxSize)
        return false;
    for (int j = L->length; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;
    L->length++;
    return true;
}
//删除顺序表中的最小值
bool DeleteMin(SqList *L,ElemType *e){
    if(L->length == 0){
        printf("顺序表为空");
        return false;
    }
    ElemType temp = L->data[0];
    int index = 0;
    for (int i = 1; i < L->length; ++i) {
        if(temp > L->data[i]){
            temp = L->data[i];
            index = i;
        }
    }
    L->data[index] = L->data[L->length - 1];
    L->length--;
    *e = temp;
    return true;
}
//查看顺序表的数据
void ListPrint(SqList *L){
    for (int i = 0; i < InitSize; ++i) {
        printf("第%d个位置,元素为:%d\n",i,L->data[i]);
    }
}
int main(){
    SqList L;
    InitList(&L);
    //插入模拟数据
    for (int i = 1; i <= 10; i++) {
        ListInsert(&L,i,rand() % 10);
    }
    ListPrint(&L);
    //删除最后一个元素
    ElemType e;
    DeleteMin(&L,&e);
    printf("最小值为：%d\n=========\n",e);
    ListPrint(&L);


}