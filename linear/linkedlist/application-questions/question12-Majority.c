//
// Created by Serendipity on 2022/8/14.
//
/**
 * �㷨�������˼�룺
 * ��ǰ����ɨ������Ԫ�أ���ǳ�һ�����ܳ�Ϊ��Ԫ�ص�Ԫ��num��Ȼ�����¼�����ȷ��num�Ƿ�����Ԫ��
 * 1��ѡȡ��ѡ����Ԫ�ء�����ɨ�����������е�ÿ������������һ������������Num���浽c�У���¼Num�ĳ��ִ���Ϊ1��
 * ����������һ�������Ե���Num���������1�����������1������������0ʱ������������һ���������浽c�У��������¼�Ϊ1��
 * ��ʼ��һ�ּ��������ӵ�ǰλ�ÿ�ʼ�ظ��������̣�ֱ��ɨ����ȫ������Ԫ�ء�
 * 2���ж�c��Ԫ���Ƿ�����������Ԫ�أ��ٴ�ɨ������飬ͳ��c��Ԫ�س��ֵĴ�����������n/2����Ϊ��Ԫ�أ����������в�������Ԫ��
 */
#include <stdio.h>
#define ElemType int
int Majority(ElemType A[],int n){
    int i,result,count = 1;        //result���������ѡ��Ԫ�أ�count��������
    result = A[0];                 //����A[0]Ϊ��ѡ��Ԫ��
    for (i = 1; i < n; i++) {      //���Һ�ѡ��Ԫ��
        if(A[i] == result){
            count++;               //��A�еĺ�ѡ��Ԫ�ؼ���
        } else{
            if(count > 0){         //�����Ǻ�ѡ��Ԫ�ص����
                count--;
            } else{                //������Ԫ�أ����¼���
                count = 1;
                result = A[i];
            }

        }
    }
    if(count > 0){
        for (i = 0; i < n; i++) {         //ͳ�ƺ�ѡ��Ԫ�ص�ʵ�ʳ��ִ���
            if(A[i] == result){
                count++;
            }
        }
    }
    if(count > n / 2)
        return result;                    //ȷ�Ϻ�ѡ��Ԫ��
    return -1;                            //��������Ԫ��
}
void ArrayPrint(ElemType A[],int len){
    for (int i = 0; i < len; ++i) {
        printf("��%d��Ԫ���ǣ�%d\n",i,A[i]);
    }
}
int main(){
    ElemType A[] = {0,5,5,3,5,7,5,5};
    ElemType B[] = {0,5,5,3,5,1,5,7};
    int len = sizeof(A) / sizeof(A[0]);
    int result = Majority(B,len);
    printf("��Ԫ���ǣ�%d\n",result);

}