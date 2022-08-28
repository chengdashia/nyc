//
// Created by Serendipity on 2022/8/12.
//
/**
 * ���Ա�(a1,a2,a3,...,an)�е�Ԫ�ص��������Ұ�˳��洢�ڼ�����ڡ�
 * Ҫ�����һ���㷨�����������ʱ���ڱ��в��ҵ���ֵΪx��Ԫ�أ����ҵ�����������Ԫ��λ�û�����
 * ���Ҳ��������������в�ʹ����Ԫ���Ե�������
*/
#include <stdio.h>
#define ElemType int

int FindMedian(ElemType A[],ElemType B[],int len){
    if(A[len - 1] < B[len - 1]){
        return B[0];
    }
    int i = 0,j = 0,k = 0,result;
    while (k < len){
        if(A[i] > B[j]){
            result = B[j++];
        } else{
            result = A[i++];
        }
        k++;
    }
    return result;
}
/**
 * �ֱ���������������A��B����λ������Ϊa��b��������A��B����λ��
 * 1����a = b����a��b�����������λ�����㷨����
 * 2����a < b������������A�н�С��һ�룬ͬʱ��������B�еĽϴ��һ�룬Ҫ�����������ĳ������
 * 3����a > b������������A�нϴ��һ�룬ͬʱ��������B�еĽ�С��һ�룬Ҫ�����������ĳ������
 */
int M_Search(int A[],int B[],int len){
    int aLeft = 0,aRight = len - 1,aMid,bLeft = 0,bRight = len - 1,bMid;
    //�ֱ��ʾ����A��B����λ����ĩβ������λ��
    while (aLeft != aRight || bLeft != bRight){
        aMid = aLeft + (aRight - aLeft) / 2;
        bMid = bLeft + (bRight - bLeft) / 2;
        if(A[aMid] == B[bMid]){     //��������1
            return A[aMid];
        }
        if(A[aMid] < B[bMid]){     //��������2
            if((aLeft + aRight) % 2 == 0){      // ��Ԫ�ظ���Ϊ����
                aLeft = aMid;                 //����A�м����ǰ�Ĳ����ұ����м��
                bRight = bMid;                 //����B�м���Ժ�Ĳ����ұ����м��
            }
            else{                     // Ԫ�ظ���Ϊż��
                aLeft = aMid + 1;          // ����A�м�㼰�м����ǰ����
                bRight = bMid;              // ����B�м���Ժ󲿷��ұ����м��
            }
        } else{              //��������3
            if((bLeft + bRight) % 2 == 0){      // ��Ԫ�ظ���Ϊ����
                aRight = aMid;                 //����A�м����ǰ�Ĳ����ұ����м��
                bLeft = bMid;                 //����B�м���Ժ�Ĳ����ұ����м��
            }
            else{                     // Ԫ�ظ���Ϊż��
                aRight = aMid;              // ����A�м���Ժ󲿷��ұ����м��
                bLeft = bMid;              // ����B�м�㼰�м����ǰ����
            }
        }
    }
    return A[aLeft] < B[bLeft] ? A[aLeft] : B[bLeft];
}
//�鿴˳��������
void ListPrint(ElemType nums[],int len){
    for (int i = 0; i < len; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,nums[i]);
    }
}
int main(){
    ElemType A[] = {2,4,6,8,10};
    ElemType B[] = {11,13,15,17,19};
    int len = sizeof(A) / sizeof(A[0]);
    ListPrint(A,len);
    ElemType result = M_Search(A,B,len);
    printf("��λ��Ԫ��Ϊ:%d\n",result);
}