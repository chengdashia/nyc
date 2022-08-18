//
// Created by Serendipity on 2022/8/16.
//
#include <stdio.h>
/**
 * 计算正整数 n!
 * @param n  初始量
 * @return  n！
 */
int factorial(int n){
    if(n == 0 || n == 1)
        return 1;
    return n* factorial(n - 1);
}
int main(){

}