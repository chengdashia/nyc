//
// Created by Serendipity on 2022/8/10.
//
/**
* 从有序顺序表中删除其值在给定值s与t之间（要求s<t)的所有元素
 * 若s和t不合理或顺序表为空，则显示出错信息并退出运行
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
    if(L->length >= L->MaxSize)
        return false;
    for (int j = L->length; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;
    L->length++;
    return true;
}
//删除顺序表中的和x相同的元素
bool DeleteBetweenS_T(SqList *L,ElemType s,ElemType t){
    if(s >= t || L->length == 0)
        return false;
    int sIndex = 0;
    for (int i = 0; i < L->length; ++i) {
        if(L->data[i] >= s) break;             //寻找值大于等于s的第一个元素的下标
        sIndex++;
    }
    printf("sIndex: %d\n",sIndex);
    int jIndex = sIndex;
    for (int j = sIndex; j < L->length; j++) {
        if(L->data[j] > t) break;                 //寻找值大于t的第一个元素的下标
        jIndex++;
    }
    printf("jIndex: %d\n",jIndex);
    for (; jIndex < L->length; sIndex++,jIndex++) {
        L->data[sIndex] = L->data[jIndex];
    }
    L->length = sIndex;
    return true;
}

bool Del_s_t(SqList *L,ElemType s,ElemType t){
    //删除有序顺序表中值在给定值s与t之间的所有元素
    int i,j;
    if(s >= t || L->length == 0)
        return false;
    for (i = 0;  i < L->length && L->data[i] < s ; i++) ; //寻找值大于等于s的第一个元素
    if(i >= L->length)         //所有元素均小于s，返回
        return false;
    for(j = i; j < L->length && L->data[j] <= t ;j++);    //寻找值大于t的第一个元素
    for(;j < L->length;i++,j++)
        L->data[i] = L->data[j];      //前移，填补被删元素的位置
    L->length = i;
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
    ListPrint(&L);
    //删除所有的x元素
    printf("删除所有的x元素\n============\n");
    Del_s_t(&L,3,5);
    ListPrint(&L);
}