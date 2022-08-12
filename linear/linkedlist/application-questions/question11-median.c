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
 * 分别求两个升序序列A、B的中位数，设为a和b，求序列A、B的中位数
 * 1、若a = b，则a或b就是所求的中位数，算法结束
 * 2、若a < b，则舍弃序列A中较小的一半，同时舍弃序列B中的较大的一半，要求两次舍弃的长度相等
 * 3、若a > b，则舍弃序列A中较大的一半，同时舍弃序列B中的较小的一半，要求两次舍弃的长度相等
 */
int M_Search(int A[],int B[],int len){
    int aLeft = 0,aRight = len - 1,aMid,bLeft = 0,bRight = len - 1,bMid;
    //分别表示序列A和B的首位数、末尾数和中位数
    while (aLeft != aRight || bLeft != bRight){
        aMid = aLeft + (aRight - aLeft) / 2;
        bMid = bLeft + (bRight - bLeft) / 2;
        if(A[aMid] == B[bMid]){     //满足条件1
            return A[aMid];
        }
        if(A[aMid] < B[bMid]){     //满足条件2
            if((aLeft + aRight) % 2 == 0){      // 若元素个数为奇数
                aLeft = aMid;                 //舍弃A中间点以前的部分且保留中间点
                bRight = bMid;                 //舍弃B中间点以后的部分且保留中间点
            }
            else{                     // 元素个数为偶数
                aLeft = aMid + 1;          // 舍弃A中间点及中间点以前部分
                bRight = bMid;              // 舍弃B中间点以后部分且保留中间点
            }
        } else{              //满足条件3
            if((bLeft + bRight) % 2 == 0){      // 若元素个数为奇数
                aRight = aMid;                 //舍弃A中间点以前的部分且保留中间点
                bLeft = bMid;                 //舍弃B中间点以后的部分且保留中间点
            }
            else{                     // 元素个数为偶数
                aRight = aMid;              // 舍弃A中间点以后部分且保留中间点
                bLeft = bMid;              // 舍弃B中间点及中间点以前部分
            }
        }
    }
    return A[aLeft] < B[bLeft] ? A[aLeft] : B[bLeft];
}
//查看顺序表的数据
void ListPrint(ElemType nums[],int len){
    for (int i = 0; i < len; ++i) {
        printf("第%d个位置,元素为:%d\n",i,nums[i]);
    }
}
int main(){
    ElemType A[] = {2,4,6,8,10};
    ElemType B[] = {11,13,15,17,19};
    int len = sizeof(A) / sizeof(A[0]);
    ListPrint(A,len);
    ElemType result = M_Search(A,B,len);
    printf("中位数元素为:%d\n",result);
}