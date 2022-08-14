//
// Created by Serendipity on 2022/8/14.
//
/**
 * 算法基本设计思想：
 * 从前往后扫描数组元素，标记出一个可能成为主元素的元素num，然后重新计数，确认num是否是主元素
 * 1、选取候选的主元素。依次扫描所给数组中的每个整数，将第一个遇到的整数Num保存到c中，记录Num的出现次数为1；
 * 若遇到的下一个整数仍等于Num，则计数加1，否则计数减1；当计数减到0时，将遇到的下一个整数保存到c中，计数重新记为1，
 * 开始新一轮计数，即从当前位置开始重复上述过程，直到扫描完全部数组元素。
 * 2、判断c中元素是否是真正的主元素，再次扫描该数组，统计c中元素出现的次数，若大于n/2，则为主元素；否则，序列中不存在主元素
 */
#include <stdio.h>
#define ElemType int
int Majority(ElemType A[],int n){
    int i,result,count = 1;        //result用来保存候选主元素，count用来计数
    result = A[0];                 //设置A[0]为候选主元素
    for (i = 1; i < n; i++) {      //查找候选主元素
        if(A[i] == result){
            count++;               //对A中的候选主元素计数
        } else{
            if(count > 0){         //处理不是候选主元素的情况
                count--;
            } else{                //更换主元素，重新计数
                count = 1;
                result = A[i];
            }

        }
    }
    if(count > 0){
        for (i = 0; i < n; i++) {         //统计候选主元素的实际出现次数
            if(A[i] == result){
                count++;
            }
        }
    }
    if(count > n / 2)
        return result;                    //确认候选主元素
    return -1;                            //不存在主元素
}
void ArrayPrint(ElemType A[],int len){
    for (int i = 0; i < len; ++i) {
        printf("第%d个元素是：%d\n",i,A[i]);
    }
}
int main(){
    ElemType A[] = {0,5,5,3,5,7,5,5};
    ElemType B[] = {0,5,5,3,5,1,5,7};
    int len = sizeof(A) / sizeof(A[0]);
    int result = Majority(B,len);
    printf("主元素是；%d\n",result);

}