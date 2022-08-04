/**
 * Created by Serendipity on 2022/8/4
 * 静态分配
 */
#include <stdio.h>
#include <stdbool.h>
#define ElemType int
#define MaxSize 10   //定义最大长度
typedef struct {
    ElemType data[MaxSize];    // 用静态的
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

//按位查找操作，获取表L中第i个位置的元素的值
ElemType GetElem(SeqList *L,int i){
    if(i < 1 || i > L->length)
        return -1;
    return L->data[i - 1];
}
//在顺序表L中查找第一个元素值等于e的元素，并返回其位序
int LocateElem(SeqList *L,ElemType e){
    for (int i = 0; i < L->length; ++i)
        if(L->data[i] == e)
            return i+1;            //数组下标为i的元素等于e，返回其位序i+1
    return 0;                      //说明查找失败
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

    //查找
    int elem = LocateElem(&L,3);
    printf("位序是%d\n",elem);

}
