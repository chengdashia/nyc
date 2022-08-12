//
// Created by Serendipity on 2022/8/11.
//
/**
* 已知在一维数组A[m+n] 中依次存放两个线性表.
 * 编写一个函数，将数组中两个顺序表的位置互换
*/
#include <stdio.h>
#define ElemType int
//在数组中将两个有序顺序表位置互换
void Reverse(ElemType nums[],int left,int right,int arraySize){
    /**
     * 逆转(aleft,aleft+1,aleft+2,...aright) 为 (aright,aright-1,...,aleft)
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
     * 数组nums[m+n]中，从0到m-1存放顺序表(a1,a2,a3,...,am)
     * 从m到m+n-1存放顺序表(b1,b2,b3...bn)
     * 算法将两个表的位置互换
     */
    Reverse(nums,0,m+n-1,arraySize);
    Reverse(nums,0,n-1,arraySize);
    Reverse(nums,n,m+n-1,arraySize);
}
//查看顺序表的数据
void ListPrint(ElemType nums[]){
    for (int i = 0; i < 7; ++i) {
        printf("第%d个位置,元素为:%d\n",i,nums[i]);
    }
}
int main(){
    int nums[] = {2,1,4,1,6,7,8};
    Exchange(nums,3,4,7);
    ListPrint(nums);
}