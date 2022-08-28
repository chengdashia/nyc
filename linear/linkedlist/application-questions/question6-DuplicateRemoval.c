//
// Created by Serendipity on 2022/8/10.
//
/**
* ������˳�����ɾ��������ֵ�ظ���Ԫ�أ�ʹ��������Ԫ�ص�ֵ����ͬ
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
//������˳�����ɾ��������ֵ�ظ���Ԫ�أ�ʹ��������Ԫ�ص�ֵ����ͬ
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
//�鿴˳��������
void ListPrint(SqList *L){
    for (int i = 0; i < L->length; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,L->data[i]);
    }
}
int main(){
    SqList L;
    InitList(&L);
    //����ģ������
    for (int i = 1; i < 7; ++i) {
        ListInsert(&L,i,i+1);
    }
    ListInsert(&L,2,2);
    ListInsert(&L,2,2);
    ListInsert(&L,2,2);
    ListInsert(&L,2,2);
    ListPrint(&L);
    //ȥ��
    printf("ȥ�غ�Ԫ��\n============\n");
    GoHeavy(&L);
    ListPrint(&L);
}