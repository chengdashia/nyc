//
// Created by Serendipity on 2022/8/11.
//
/**
* ��֪��һά����A[m+n] �����δ���������Ա�.
 * ��дһ��������������������˳����λ�û���
*/
#include <stdio.h>
#define ElemType int
//�������н���������˳���λ�û���
void Reverse(ElemType nums[],int left,int right,int arraySize){
    /**
     * ��ת(aleft,aleft+1,aleft+2,...aright) Ϊ (aright,aright-1,...,aleft)
     */
    if(left >= right || right >= arraySize)
        return;
    int mid = left + (right - left) / 2;
    ElemType temp;
//    for (int i = 0; i < mid; ++i) {
//        temp = nums[i];
//        nums[i] = nums[mid - i -1];
//        nums[mid - i - 1] = temp;
//    }
    for (int i = 0; i <= mid - left; i++) {
        temp = nums[left + i];
        nums[left + 1] = nums[right - i];
        nums[right - i] = temp;
    }
}
void Exchange(ElemType nums[],int m,int n,int arraySize){
    /**
     * ����nums[m+n]�У���0��m-1���˳���(a1,a2,a3,...,am)
     * ��m��m+n-1���˳���(b1,b2,b3...bn)
     * �㷨���������λ�û���
     */
    Reverse(nums,0,m+n-1,arraySize);
    Reverse(nums,0,n-1,arraySize);
    Reverse(nums,n,m+n-1,arraySize);
}
//�鿴˳��������
void ListPrint(ElemType nums[]){
    for (int i = 0; i < 7; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,nums[i]);
    }
}
int main(){
    int nums[] = {2,1,4,1,6,7,8};
    Exchange(nums,3,4,7);
    ListPrint(nums);
}