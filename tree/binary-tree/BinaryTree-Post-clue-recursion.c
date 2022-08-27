//
// Created by Serendipity on 2022/8/27.
//
#include <stdio.h>

typedef struct ThreadNode{
    int data;
    struct ThreadNode *lchild, *rchild;
    int ltag, rtag;                // 左、右线索标志
}TreadNode, *ThreadTree;
//后序线索化 递归方式
void PostThread(ThreadTree p,ThreadTree pre){
    if(p != NULL){
        PostThread(p->lchild,pre);  //递归，线索化左子树
        PostThread(p->rchild,pre);  //递归，线索化右子树
        if(p->lchild == NULL){            //左子树为空，建立前驱线索
            p->lchild = pre;
            p->ltag = 1;
        }
        if(p->rchild == NULL && pre != NULL){
            pre->rchild = p;            //建立前驱结点和后继结点
            pre->rtag = 1;
        }
        pre = p;
    }
}
void CreateInThreadRecursion(ThreadTree T){
    ThreadTree pre = NULL;
    if(T != NULL){                           //非空二叉树，线索化
        PostThread(T,pre);         //线索化二叉树
        pre->rchild = NULL;                  //处理遍历的最后一个结点
        pre->rtag = 1;
    }
}