//
// Created by Serendipity on 2022/8/16.
//
/**
 * ����쳲���������
 * @param n  ��ʼ��
 * @return  n��
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
    printf("����ǣ�%d\n",result);
}