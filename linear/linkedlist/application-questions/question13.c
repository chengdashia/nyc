//
// Created by Serendipity on 2022/8/9.
//
#include<stdio.h>
#include <malloc.h>
#include <string.h>
int minNum(int nums[],int n){
    int i,*B;                                        // 标记数组
    B = (int *)malloc(sizeof(int) *n);          //分配空间
    memset(B,0,sizeof(int) * n);       // 赋初值为0
    for (i = 0; i < n; i++) {
        if(nums[i] > 0 && nums[i] <= n){            //若nums[i]的值在1~n之间，则标记数组B
            B[nums[i] - 1] = 1;
        }
    }
    for (i = 0; i < n; i++) {                       //扫描数组B，找到目标值
        if(B[i] == 0) break;
    }
    return i + 1;                                    //返回结果
}
int main(){
//    int nums[] = {-5,3,2,3};
    int nums[] = {1,2,3};
    int result = minNum(nums,sizeof(nums) / sizeof(nums[0]));
    printf("result:%d\n",result);


}