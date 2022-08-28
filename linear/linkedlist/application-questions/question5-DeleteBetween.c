//
// Created by Serendipity on 2022/8/10.
//
/**
* ��˳�����ɾ����ֵ�ڸ���ֵs��t֮�䣨����s��t,Ҫ��s<t)������Ԫ��
 * ��s��t�������˳���Ϊ�գ�����ʾ������Ϣ���˳�����
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
    if(L->length >= L->MaxSize)
        return false;
    for (int j = L->length; j >= i ; j--) {
        L->data[j] = L->data[j - 1];
    }
    L->data[i - 1] = e;
    L->length++;
    return true;
}
//ɾ������˳�����ֵ�ڸ���ֵs��t֮�������Ԫ��
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
    ListInsert(&L,4,2);
    ListPrint(&L);
    //ɾ�����е�xԪ��
    printf("ɾ�����е�λ��3,5֮���Ԫ��\n============\n");
    Del_s_t(&L,3,5);
    ListPrint(&L);
}