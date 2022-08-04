/**
 * Created by Serendipity on 2022/8/4
 * 静态分配
 */
#include <stdio.h>
#include <stdbool.h>

#define MaxSize 10   //定义最大长度
typedef struct {
    int data[MaxSize];    // 用静态的
    int length;         // 顺序表的当前长度
}SeqList;
//基本操作——初始化一个顺序表
void InitList(SeqList *L){
    for(int i = 0;i < MaxSize; i++){
        L->data[i] = 1;          //将所有的数据元素设置为默认初始值
    }
    L->length = 0;    //顺序表的初始化长度为0
}
//插入
bool ListInsert(SeqList *L,int i,int e){
    if(i < 1 || i > L->length + 1)     //判断i的范围是否有效
        return false;
    if(L->length >= MaxSize)           //当前存储空间已满，不能插入
        return false;
    for (int j = L->length;j >= i;j--)          //将i个元素及以后的元素后移
        L->data[j] = L->data[j - 1];
    L->data[i-1] = e;   //在位置i处放入e
    L->length++;        //长度+1
    return true;
}
//删除
bool ListDelete(SeqList *L,int i,int *e){
    if(i < 1 || i > L->length)     //判断i的范围是否有效
        return false;
    *e = L->data[i-1];   //将被删除的元素赋值为e
    for (int j = i; j <= L->length; j++)          //将i个元素及以后的元素前移
        L->data[j - 1] = L->data[j];
    L->length--;        //长度-1
    return true;
}
//查看顺序表的数据
void ListPrint(SeqList *L){
    for (int i = 0; i < MaxSize; ++i) {
        printf("第%d个位置,元素为:%d\n",i,L->data[i]);
    }
}

int main(){
    SeqList L;       //声明一个顺序表
    InitList(&L);  //初始化顺序表
    //插入
    for (int i = 0; i < 4; ++i) {
        ListInsert(&L,i,i+1);
    }
    ListPrint(&L);

    //删除
    int e = -1;
    if(ListDelete(&L,1,&e))
        printf("已经删除第3个元素，删除的元素为%d\n",e);
    else
        printf("位序i不合法,删除失败\n");
    ListPrint(&L);

}
