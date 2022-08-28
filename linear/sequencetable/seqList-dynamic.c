/**
 * Created by Serendipity on 2022/8/4
 * ��̬����
 */
#include <stdio.h>
#include <malloc.h>

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
//���Ӷ�̬����ĳ���
void IncreaseSize(SeqList *L,int len){
    int *p = L->data;
    L->data = (ElemType *) malloc((L->MaxSize + len) * sizeof(ElemType));
    for (int i = 0; i < L->length; ++i) {
        L->data[i] = p[i];        //�����ݸ��Ƶ�������
    }
    L->MaxSize = L->MaxSize + len;  //˳�����󳤶�����len
    free(p);               //�ͷ�ԭ�������ݿռ�
}
//��λ���Ҳ�������ȡ��L�е�i��λ�õ�Ԫ�ص�ֵ
ElemType GetElem(SeqList *L,int i){
    if(i < 1 || i > L->length)
        return -1;
    return L->data[i - 1];
}
int main(){
    SeqList L;       //����һ��˳���
    InitList(&L);  //��ʼ��˳���

    int result =  GetElem(&L,3);
    printf("�����:%d\n",result);


}