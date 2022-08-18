//
// Created by Serendipity on 2022/8/16.
//
/**
 * 计算斐波那契数列
 * @param n  初始量
 * @return  n！
 */
#include <stdio.h>
int Fib(int n){
    if(n == 0)
        return 0;
    else if(n == 1)
        return 1;
    else
        return Fib(n - 1) + Fib(n - 2);
}
int main(){
    int result = Fib(4);
    printf("结果是：%d\n",result);
}