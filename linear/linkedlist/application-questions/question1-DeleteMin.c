//
// Created by Serendipity on 2022/8/10.
//
/**
* ��˳�����ɾ��������Сֵ��Ԫ��(����Ψһ)���ɺ������ر�ɾԪ�ص�ֵ���ճ���λ�������һ��Ԫ�������˳���Ϊ�գ�
 * ����ʾ������Ϣ���˳�����
*/
#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

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
//ɾ��˳����е���Сֵ
bool DeleteMin(SqList *L,ElemType *e){
    if(L->length == 0){
        printf("˳���Ϊ��");
        return false;
    }
    ElemType temp = L->data[0];
    int index = 0;
    for (int i = 1; i < L->length; ++i) {
        if(temp > L->data[i]){
            temp = L->data[i];
            index = i;
        }
    }
    L->data[index] = L->data[L->length - 1];
    L->length--;
    *e = temp;
    return true;
}
//�鿴˳��������
void ListPrint(SqList *L){
    for (int i = 0; i < InitSize; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,L->data[i]);
    }
}
int main(){
    SqList L;
    InitList(&L);
    //����ģ������
    for (int i = 1; i <= 10; i++) {
        ListInsert(&L,i,rand() % 10);
    }
    ListPrint(&L);
    //ɾ�����һ��Ԫ��
    ElemType e;
    DeleteMin(&L,&e);
    printf("��СֵΪ��%d\n=========\n",e);
    ListPrint(&L);


}