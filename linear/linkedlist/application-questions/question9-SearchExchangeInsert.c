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
void SearchExchangeInsert(ElemType nums[],int len,ElemType x){
    int left = 0,right = len - 1,mid;
    while (left <= right){
        mid = left + (right - left) / 2;      //找中间位置
        if(nums[mid] == x){                   //找到x，退出while循环
            break;
        } else if(nums[mid] > x){             //到中点的左半边去查
            right = mid - 1;
        } else{                               //到中点的右半边去查
            left = mid + 1;
        }
    }
    if(nums[mid] == x && mid != len - 1){         //若最后一个元素与x相等，则不存在与其后继交换的操作
        ElemType temp = nums[mid];
        nums[mid] = nums[mid + 1];
        nums[mid + 1] = temp;
    }
    if(left > right){                 //查找失败，插入数据元素x
        int i;
        for (i = len - 1; i > right; i--) {
            nums[i + 1] = nums[i];                //后移元素
        }
        nums[i + 1] = x;           //插入x
    }
}
//查看顺序表的数据
void ListPrint(ElemType nums[]){
    int len = sizeof(nums) / sizeof(nums[0]);
    for (int i = 0; i < len; ++i) {
        printf("第%d个位置,元素为:%d\n",i,nums[i]);
    }
}
int main(){
    ElemType nums[] = {1,2,3,4,5,7,8};
    int len = sizeof(nums) / sizeof(nums[0]);
    SearchExchangeInsert(nums,len,6);
    ListPrint(nums);
}