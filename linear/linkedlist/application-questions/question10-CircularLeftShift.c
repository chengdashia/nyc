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

void Convert(ElemType nums[],int left,int right){
    int i, temp;
    for (i = 0; i < (right - left + 1) / 2; i++) {
        temp = nums[left+i];
        nums[left+i] = nums[right-i];
        nums[right - i] = temp;
    }
}
void CycleLeft(ElemType nums[],int len,int p){
    Convert(nums,0,p-1);
    Convert(nums,p,len-1);
    Convert(nums,0,len - 1);
}
//�鿴˳��������
void ListPrint(ElemType nums[],int len){
    for (int i = 0; i < len; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,nums[i]);
    }
}
int main(){
    ElemType nums[] = {1,2,3,4,5,7,8};
    int len = sizeof(nums) / sizeof(nums[0]);
    CycleLeft(nums,len,3);
    ListPrint(nums,len);
}