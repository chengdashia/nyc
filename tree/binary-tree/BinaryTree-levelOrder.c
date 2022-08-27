//
// Created by Serendipity on 2022/8/25.
//
#include <stdio.h>
#include <stdbool.h>
#include <malloc.h>

#define ElemType int
//二叉树的结点(链式存储)
typedef struct BiTNode{
    ElemType data;
    struct BiTNode *lchild, *rchild;
}BiTNode,*BiTree;
//链式队列结点
typedef struct LinkNode{
    BiTNode *data;
    struct LinkNode *next;
}LinkNode;

typedef struct {
    LinkNode *front,*rear;
}LinkQueue;

/**
 * 初始化队列(带头结点)
 * @param Q
 */
void InitQueue(LinkQueue *Q){
    //初始化时，front、rear 都指向NULL
    Q->front = Q->rear = NULL;
}
/**
 * 判断队列是否为空
 * @param Q
 * @return
 */
bool isEmpty(LinkQueue Q){
    if(Q.front == NULL)
        return true;
    return false;
}
/**
* 新元素入队
*/
bool EnQueue(LinkQueue *Q,ElemType x){
    LinkNode *s = (LinkNode *) malloc(sizeof(LinkNode));
    if(s == NULL)
        return false;
    s->data->data = x;
    s->next = NULL;
    if(isEmpty(*Q)){        //在空队列中插入第一个元素
        Q->front = s;           //修改对头对头指针
        Q->rear = s;
    } else{
        Q->rear->next = s;         //新结点插入到rear之后
        Q->rear = s;              //修改对尾指针
    }
    return true;
}
/**
 * 对头元素出列
 */
bool DeQueue(LinkQueue *Q,ElemType *x){
    if(isEmpty(*Q))             //对列为空
        return false;
    LinkNode *p = Q->front->next;  //队头指向的第一个元素
    *x = p->data->data;                  //用变量x返回队头元素
    Q->front->next = p->next;      //修改队头指向的第一个元素。即第二个元素
    if(Q->rear == p)               // 此次是最后一个结点出队
        Q->rear = Q->front = NULL; //front 指向NULL  rear 指向NULL
    free(p);               //释放结点空间
    return true;

}
void visit(BiTree P){

}
//层次遍历
/**
 * 算法思想：
初始化一个辅助队列
根节点入队
若队列非空，则队头结点出队，访问该结点，依次将其左、右孩子插入队尾（如果有的话）
重复以上操作直至队列为空
 * @param T
 */
void LevelOrder(BiTree T){
    LinkQueue Q;
    InitQueue(&Q);      //初始化辅助队列
    BiTree P;
    EnQueue(&Q,T->data);   //将根节点入栈
    while (!isEmpty(Q)){         //队列不空则循环
        DeQueue(&Q,&T->data);  //对头结点出队
        visit(P);                    //访问出队结点
        if(P->lchild != NULL){
            EnQueue(&Q,P->lchild->data);     //左孩子入队
        }
        if(P->rchild != NULL){
            EnQueue(&Q,P->rchild->data);    //右孩子入队
        }
    }
}