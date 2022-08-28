//
// Created by Serendipity on 2022/8/9.
//
/**
 * ���һ����Ч���㷨����˳���L������Ԫ������
 */
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>
#define InitSize 10
#define ElemType int
typedef struct {
    ElemType *data;     //ָʾ��̬���������ָ��
    int MaxSize;        //˳�����������
    int length;         // ˳���ĵ�ǰ����
}SeqList;              //˳�������Ͷ��壨��̬���䷽ʽ��
//��������������ʼ��һ��˳���
void InitList(SeqList *L){
    //malloc ��������һ��ָ�룬��Ҫǿ��ת��Ϊ�㶨�������Ԫ������ָ��
    //InitSize  malloc �����Ĳ�����ָ��Ҫ������������ڴ�ռ�
    L->data = (ElemType *)malloc(sizeof(ElemType) * InitSize);
    L->length = 0;    //˳���ĳ�ʼ������Ϊ0
    L->MaxSize = InitSize;
}
//��̬��չ˳���ĳ���
void IncreaseSize(SeqList *L,int len){
    int *temp = L->data;
    L->data = (ElemType *) malloc(sizeof(ElemType) * (L->MaxSize + len));
    for (int i = 0; i < L->length; ++i) {
        L->data[i] = temp[i];
    }
    L->MaxSize = L->MaxSize + len;
    free(temp);
}
//����
bool ListInsert(SeqList *L,int i,int e){
    if(i < 1 || i > L->length + 1)         //�ж�i�ķ�Χ�Ƿ���Ч
        return false;
    if(L->length >= InitSize)              //��ǰ�Ĵ洢�ռ����������ܲ���
        return false;
    for (int j = L->length - 1; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;                //��λ��i������e
    return true;

}
//�鿴˳��������
void ListPrint(SeqList *L){
    for (int i = 0; i < L->MaxSize; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,L->data[i]);
    }
}
//��˳���L������Ԫ������
void InverseList(SeqList *L){
    ElemType temp;
    for (int i = 0; i < L->length / 2; ++i) {
        temp = L->data[i];
        L->data[i] = L->data[L->length - i - 1];
        L->data[L->length - i - 1] = temp;
    }
}
int main(){
    SeqList L;
    InitList(&L);
    //����
    for (int i = 1; i <= 10; ++i) {
        ListInsert(&L,i,i+1);
    }
    ListPrint(&L);

    printf("===============���ú�==========\n");
    InverseList(&L);
    ListPrint(&L);
}