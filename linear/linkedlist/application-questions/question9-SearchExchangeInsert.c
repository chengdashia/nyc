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
void SearchExchangeInsert(ElemType nums[],int len,ElemType x){
    int left = 0,right = len - 1,mid;
    while (left <= right){
        mid = left + (right - left) / 2;      //���м�λ��
        if(nums[mid] == x){                   //�ҵ�x���˳�whileѭ��
            break;
        } else if(nums[mid] > x){             //���е������ȥ��
            right = mid - 1;
        } else{                               //���е���Ұ��ȥ��
            left = mid + 1;
        }
    }
    if(nums[mid] == x && mid != len - 1){         //�����һ��Ԫ����x��ȣ��򲻴��������̽����Ĳ���
        ElemType temp = nums[mid];
        nums[mid] = nums[mid + 1];
        nums[mid + 1] = temp;
    }
    if(left > right){                 //����ʧ�ܣ���������Ԫ��x
        int i;
        for (i = len - 1; i > right; i--) {
            nums[i + 1] = nums[i];                //����Ԫ��
        }
        nums[i + 1] = x;           //����x
    }
}
//�鿴˳��������
void ListPrint(ElemType nums[]){
    int len = sizeof(nums) / sizeof(nums[0]);
    for (int i = 0; i < len; ++i) {
        printf("��%d��λ��,Ԫ��Ϊ:%d\n",i,nums[i]);
    }
}
int main(){
    ElemType nums[] = {1,2,3,4,5,7,8};
    int len = sizeof(nums) / sizeof(nums[0]);
    SearchExchangeInsert(nums,len,6);
    ListPrint(nums);
}