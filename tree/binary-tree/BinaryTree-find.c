//
// Created by Serendipity on 2022/8/27.
//
/**
* 中序线索二叉树找中序后继
*/
#include <stdio.h>
typedef struct ThreadNode{
    int data;
    struct ThreadNode *lchild, *rchild;
    int ltag, rtag;                // 左、右线索标志
}TreadNode, *ThreadTree;

//找到以p为跟的子树中，第一个被中序遍历的结点
TreadNode *FirstNode(TreadNode *p){
    //循环找到最左下的结点(不一定是叶子结点)
    while (p->ltag == 0){
        p = p->lchild;
    }
    return p;
}

//找到以p为跟的子树中，最后一个被中序遍历的结点
TreadNode *LastNode(TreadNode *p){
    //循环找到最左下的结点(不一定是叶子结点)
    while (p->rtag == 0){
        p = p->rchild;
    }
    return p;
}

//在中序线索二叉树中找到结点p的后继结点
TreadNode *NextNode(TreadNode *p){
    //右子树最左下结点
    if(p->rtag == 0){
        return FirstNode(p->rchild);
    } else{ //rtag == 1 直接返回后继线索
        return p->rchild;
    }
}

//在中序线索二叉树中找到结点p的后继结点
TreadNode *PreNode(TreadNode *p){
    //在子树最左下结点
    if(p->ltag == 0){
        return FirstNode(p->lchild);
    } else{ //rtag == 1 直接返回后继线索
        return p->lchild;
    }
}
void visit(TreadNode *p){

}

//对中序线索二叉树进行中序遍历(利用线索实现的非递归算法)
void Inorder(TreadNode *T){
    for(TreadNode *p = FirstNode(T);p != NULL;p = NextNode(p)){
        visit(p);
    }
}

//对中序线索二叉树进行先序遍历(利用线索实现的非递归算法)
void Preorder(TreadNode *T){
    for(TreadNode *p = LastNode(T);p != NULL;p = PreNode(p)){
        visit(p);
    }
}