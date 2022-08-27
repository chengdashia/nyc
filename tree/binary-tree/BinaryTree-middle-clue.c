//
// Created by Serendipity on 2022/8/25.
//
#include <stdio.h>

typedef struct ThreadNode{
    int data;
    struct ThreadNode *lchild, *rchild;
    int ltag, rtag;                // 左、右线索标志
}TreadNode, *ThreadTree;

//全局变量pre, 指向当前访问的结点的前驱
TreadNode *pre=NULL;

void visit(TreadNode *q){
    if(q->lchild == NULL){                 //左子树为空，建立前驱线索
        q->lchild = pre;
        q->ltag = 1;
    }

    if(pre!=NULL && pre->rchild != NULL){
        pre->rchild = q;           //建立前驱结点的后继线索
        pre->rtag = 1;
    }
    pre = q;
}
//中序遍历
void InThread(ThreadTree T){
    if(T!=NULL){
        InThread(T->lchild);    //中序遍历左子树
        visit(T);               //访问根节点
        InThread(T->rchild);    //中序遍历右子树
    }
}

//中序线索化二叉树T
void CreateInThread(ThreadTree T){
    pre = NULL;                //pre初始为NULL
    if(T!=NULL){              //非空二叉树才能进行线索化
        InThread(T);            //中序线索化二叉树
        if(pre->rchild == NULL)
            pre->rtag=1;         //处理遍历的最后一个结点
    }
}



