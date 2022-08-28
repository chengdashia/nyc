//
// Created by Serendipity on 2022/8/10.
//
/**
 * �Գ���Ϊn��˳���L����дһ��ʱ�临�Ӷ�ΪO(n),�ռ临�Ӷ�ΪO(1)���㷨��
 * ���㷨ɾ�����Ա�������ֵΪx������Ԫ��
 */
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

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
    if(L->length >= L->MaxSize)
        return false;
    for (int j = L->length - 1; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;
    L->length++;
    return true;
}
//ɾ��˳����еĺ�x��ͬ��Ԫ��
bool DeleteAllSameX(SqList *L,ElemType x){
    printf("˳���ĳ���Ϊ��%d\n",L->length);
    int k = 0;
    for (int i = 0; i < L->length; ++i) {
        if(L->data[i] != x){
            L->data[k++] = L->data[i];
        }
    }
    L->length = k;
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
    ListInsert(&L,1,2);
    ListInsert(&L,2,5);
    ListInsert(&L,3,6);
    ListInsert(&L,4,1);
    ListInsert(&L,5,6);
    ListInsert(&L,6,6);
    ListPrint(&L);
    //ɾ�����е�xԪ��
    printf("ɾ�����е�xԪ��\n============\n");
    ElemType x = 6;
    DeleteAllSameX(&L,x);
    ListPrint(&L);
}