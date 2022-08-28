/**
 * Created by Serendipity on 2022/8/4
 * ��̬����
 */
#include <stdio.h>
#include <stdbool.h>

#define MaxSize 10   //������󳤶�
typedef struct {
    int data[MaxSize];    // �þ�̬��
    int length;         // ˳���ĵ�ǰ����
}SeqList;
//��������������ʼ��һ��˳���
void InitList(SeqList *L){
    for(int i = 0;i < MaxSize; i++){
        L->data[i] = 1;          //�����е�����Ԫ������ΪĬ�ϳ�ʼֵ
    }
    L->length = 0;    //˳���ĳ�ʼ������Ϊ0
}
//����
bool ListInsert(SeqList *L,int i,int e){
    if(i < 1 || i > L->length + 1)     //�ж�i�ķ�Χ�Ƿ���Ч
        return false;
    if(L->length >= MaxSize)           //��ǰ�洢�ռ����������ܲ���
        return false;
    for (int j = L->length;j >= i;j--)          //��i��Ԫ�ؼ��Ժ��Ԫ�غ���
        L->data[j] = L->data[j - 1];
    L->data[i-1] = e;   //��λ��i������e
    L->length++;        //����+1
    return true;
}
//ɾ��
bool ListDelete(SeqList *L,int i,int *e){
    if(i < 1 || i > L->length)     //�ж�i�ķ�Χ�Ƿ���Ч
        return false;
    *e = L->data[i-1];   //����ɾ����Ԫ�ظ�ֵΪe
    for (int j = i; j <= L->length; j++)          //��i��Ԫ�ؼ��Ժ��Ԫ��ǰ��
        L->data[j - 1] = L->data[j];
    L->length--;        //����-1
    return true;
}
//�鿴˳��������
void ListPrint(SeqList *L){
    for (int i = 0; i < MaxSize; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,L->data[i]);
    }
}

int main(){
    SeqList L;       //����һ��˳���
    InitList(&L);  //��ʼ��˳���
    //����
    for (int i = 0; i < 4; ++i) {
        ListInsert(&L,i,i+1);
    }
    ListPrint(&L);

    //ɾ��
    int e = -1;
    if(ListDelete(&L,1,&e))
        printf("�Ѿ�ɾ����3��Ԫ�أ�ɾ����Ԫ��Ϊ%d\n",e);
    else
        printf("λ��i���Ϸ�,ɾ��ʧ��\n");
    ListPrint(&L);

}
