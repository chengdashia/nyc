//
// Created by Serendipity on 2022/8/10.
//
/**
* 从顺序表中删除其值在给定值s与t之间（包含s和t,要求s<t)的所有元素
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
//删除有序顺序表中值在给定值s与t之间的所有元素
bool Del_s_t(SqList *L,ElemType s,ElemType t){
    if(s >= t || L->length == 0)
        return false;
    int i,k = 0;
    for(i = 0;i < L->length;i++){
        if(L->data[i] >= s && L->data[i] <= t){
            k++;
        } else{
            L->data[i - k] = L->data[i];
        }
    }
    L->length -= k;
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
    ListInsert(&L,4,2);
    ListPrint(&L);
    //删除所有的x元素
    printf("删除所有的位于3,5之间的元素\n============\n");
    Del_s_t(&L,3,5);
    ListPrint(&L);
}