//
// Created by Serendipity on 2022/8/12.
//
/**
 * 线性表(a1,a2,a3,...,an)中的元素递增有序且按顺序存储于计算机内。
 * 要求设计一个算法，完成用最少时间在表中查找到数值为x的元素，若找到，则将其与后继元素位置互换，
 * 若找不到，则将其插入表中并使表中元素仍递增有序
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
//查看顺序表的数据
void ListPrint(ElemType nums[],int len){
    for (int i = 0; i < len; ++i) {
        printf("第%d个位置,元素为:%d\n",i,nums[i]);
    }
}
int main(){
    ElemType nums[] = {1,2,3,4,5,7,8};
    int len = sizeof(nums) / sizeof(nums[0]);
    CycleLeft(nums,len,3);
    ListPrint(nums,len);
}