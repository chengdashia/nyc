//
// Created by Serendipity on 2022/8/13.
//
/**
 * ��ͷ���
 */
#include <stdio.h>
#include <stdbool.h>
#include <malloc.h>

#define ElemType int
typedef struct StackNode{
    ElemType data;                        //������
    struct StackNode *next;              //ָ����
}StackNode,*LiStack;                            //ջ���Ͷ���
//ջ��ʼ��
bool InitLiStack(LiStack *S){
    *S = (StackNode *) malloc(sizeof(StackNode));
    if(S == NULL)
        return false;
    (*S)->next = NULL; //����ջ��ָ��Ϊ��
    return true;
}
//�п�
bool StackEmpty(LiStack *S){
    if((*S)->next == NULL)     //���ͷ���ָ�����һ��ΪNULL
        return true;
    return false;
}
//��ջ push
bool Push(LiStack *S,ElemType x){
    StackNode *s = (StackNode *)malloc(sizeof(StackNode));
    if(s == NULL)
        return false;     //������ռ�ʧ��,����ʧ�ܣ���������false
    s->next = (*S)->next; //�����½���ָ���
    s->data = x;         //�����½���������
    (*S)->next = s;      //����ͷ���ָ���ָ���µ�ջ��Ԫ��
    return true;
}
//��ջ pop
bool pop(LiStack *S,ElemType *x){
    if((*S)->next == NULL)
        return false;
    StackNode *p = (*S)->next;    //��ԭջ������Ԫ�ص���������p
    *x = p->data;                //��ջ��Ԫ�ظ���x
    (*S)->next = p->next;       //ջ��ָ��ָ����ջ�е���һ������Ԫ��
    p->next = NULL;
    free(p);   //�ͷ�ջ��Ԫ����ռ�Ŀռ�
    return true;
}
//��ȡջ��Ԫ��
bool GetTopElem(LiStack S,ElemType *x){
    if(S->next){              //���ջ��Ϊ��
        *x = S->next->data;   //��ջ��Ԫ�ظ�ֵ��x
        return true;
    }
    return false;
}
//��ȡջ��
int StackLength(LiStack S){
    int count = 0;
    while (S->next != NULL){
        ++count;
        S = S->next;
    }
    return count;


}
int main(){
    int i,t,x,a[20];
    LiStack S;
    InitLiStack(&S);//��ʼ��ջ
    printf("����������5����,��ʼ��ջ��\n");
    for(i=0;i<5;i++)
    {
        scanf("%d",&a[i]);
        Push(&S,a[i]);
        GetTopElem(S,&x);
        if(x!=-1)
        {
            printf("��ǰջ��Ԫ��Ϊ%d\n",x);
        }
    }
    printf("��ջ����\n");
    printf("ջ��Ϊ%d\n",StackLength(S));
    printf("��ʼ��ջ:\n");
    for (i=0;i<5;i++)
    {
        pop(&S,&t);
        printf("%d",t);
    }
    printf("\n");
    printf("��ջ��ջ��Ϊ%d\n",StackLength(S));

}