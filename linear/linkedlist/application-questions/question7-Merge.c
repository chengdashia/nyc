//
// Created by Serendipity on 2022/8/11.
//
/**
* ��������ѧ˳���ϲ�Ϊһ���µ�����˳������ɺ������ؽ��˳���
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
//��ʼ��һ��˳���
void InitList(SqList *L){
    L->data = (ElemType*)malloc(sizeof(ElemType) * InitSize);
    L->length = 0;
    L->MaxSize = InitSize;
    for (int i = 0; i < L->MaxSize; ++i) {
        L->data[i] = 0;
    }
}
//����
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
// ����������˳���ϲ�Ϊһ���µ�����˳������ɺ������ؽ��˳���
bool Merge(SqList L1,SqList L2,SqList *L){
    if(L1.length == 0){        //����L1�ĳ���Ϊ0��ϲ����˳���ֻ��L2
        L->data = L2.data;
        L->length = L2.length;
        return true;
    }
    if(L2.length == 0){         //����L2�ĳ���Ϊ0��ϲ����˳���ֻ��L1
        L->data = L1.data;
        L->length = L1.length;
        return true;
    }
    if(L1.length + L2.length > InitSize)    //������������ĳ��ȴ���˳������󳤶ȣ����ܺϲ�
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
//�鿴˳��������
void ListPrint(SqList L){
    for (int i = 0; i < L.length; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,L.data[i]);
    }
}
int main(){
    SqList L1,L2,L;
    InitList(&L1);
    InitList(&L2);
    InitList(&L);
    //����ģ������
    for (int i = 1; i < 5; ++i) {
        ListInsert(&L1,i,i+1);
//        ListInsert(&L2,i,i+2);
    }
    printf("\n======L1======\n");
    ListPrint(L1);
    printf("\n======L2======\n");
    ListPrint(L2);
    //ȥ��
    printf("\n======�ϲ���======\n");
    Merge(L1,L2,&L);
    ListPrint(L);


}