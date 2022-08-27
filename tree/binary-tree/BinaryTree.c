//
// Created by Serendipity on 2022/8/25.
//
#include <stdio.h>
#define ElemType int
typedef struct BiTNode{
    ElemType data;                      //数据域
    struct BiTNode *lchild,*rchild;     //左右孩子指针
}BiTNode,*BiTree;
void visit(BiTree T){

}
//前序遍历(根->左->右)
void PreOrder(BiTree T){
    if(T != NULL){
        visit(T);         //访问根节点
        PreOrder(T->lchild);  //递归遍历左子树
        PreOrder(T->rchild);  //递归遍历右子树
    }
}
// 中序遍历(左->根->右)
void InOrder(BiTree T){
    if(T != NULL){
        InOrder(T->lchild);  //递归遍历左子树
        visit(T);         //访问根节点
        InOrder(T->rchild);  //递归遍历右子树
    }
}
// 中序遍历(左->右->根)
void PostOrder(BiTree T){
    if(T != NULL){
        PostOrder(T->lchild);  //递归遍历左子树
        PostOrder(T->rchild);  //递归遍历右子树
        visit(T);         //访问根节点
    }
}
int main(){

}