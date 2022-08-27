//
// Created by Serendipity on 2022/8/25.
//
#include <stdbool.h>

#define MaxSize 100
#define ElemType int
struct TreeNode{
    ElemType data;  //结点中的数据
    bool isEmpty;   //结点是否为空
};
int main(){
    struct TreeNode t[MaxSize];
    for (int i = 0; i < MaxSize; ++i) {
        t[i].isEmpty = true;
    }
}