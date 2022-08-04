//
// Created by Serendipity on 2022/8/4.
//
#include <stdio.h>
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
int main(){
    SeqList L;       //声明一个顺序表
    InitList(&L);  //初始化顺序表
    for (int i = 0; i < MaxSize; ++i) {
        printf("%d\n",L.data[i]);
    }

}
