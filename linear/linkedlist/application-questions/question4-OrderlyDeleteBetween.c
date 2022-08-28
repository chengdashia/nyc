//
// Created by Serendipity on 2022/8/10.
//
/**
* ������˳�����ɾ����ֵ�ڸ���ֵs��t֮�䣨Ҫ��s<t)������Ԫ��
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
//ɾ��˳����еĺ�x��ͬ��Ԫ��
bool DeleteBetweenS_T(SqList *L,ElemType s,ElemType t){
    if(s >= t || L->length == 0)
        return false;
    int sIndex = 0;
    for (int i = 0; i < L->length; ++i) {
        if(L->data[i] >= s) break;             //Ѱ��ֵ���ڵ���s�ĵ�һ��Ԫ�ص��±�
        sIndex++;
    }
    printf("sIndex: %d\n",sIndex);
    int jIndex = sIndex;
    for (int j = sIndex; j < L->length; j++) {
        if(L->data[j] > t) break;                 //Ѱ��ֵ����t�ĵ�һ��Ԫ�ص��±�
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
    //ɾ������˳�����ֵ�ڸ���ֵs��t֮�������Ԫ��
    int i,j;
    if(s >= t || L->length == 0)
        return false;
    for (i = 0;  i < L->length && L->data[i] < s ; i++) ; //Ѱ��ֵ���ڵ���s�ĵ�һ��Ԫ��
    if(i >= L->length)         //����Ԫ�ؾ�С��s������
        return false;
    for(j = i; j < L->length && L->data[j] <= t ;j++);    //Ѱ��ֵ����t�ĵ�һ��Ԫ��
    for(;j < L->length;i++,j++)
        L->data[i] = L->data[j];      //ǰ�ƣ����ɾԪ�ص�λ��
    L->length = i;
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
    ListPrint(&L);
    //ɾ�����е�xԪ��
    printf("ɾ�����е�xԪ��\n============\n");
    Del_s_t(&L,3,5);
    ListPrint(&L);
}