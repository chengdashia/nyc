//
// Created by Serendipity on 2022/8/15.
//
/**
* ����ƥ�� ֱ��������ʵ��
*/
#include <stdio.h>
#include <stdbool.h>

#define ElemType char
bool bracketCheck(ElemType str[],int length){
    ElemType tempStack[length];
    int index = 0;
    for (int i = 0; i < length; i++) {
        if(str[i] == '(' || str[i] == '[' || str[i] == '{'){
            tempStack[index++] = str[i];
        } else{
            if(index == 0){
                return false;
            }
            char elemTop = tempStack[index--];
            if(str[i] == '(' && elemTop != ')')
                return false;
            if(str[i] == '[' && elemTop != ']')
                return false;
            if(str[i] == '{' && elemTop != '}')
                return false;
        }
    }
    return index == 0;
}
int main(){
    ElemType str[] = "[]";
    int len = sizeof(str)/sizeof(str[0]);
    bool result = bracketCheck(str,len);
    printf("�Ƿ�ƥ�䣺��%d\n",result);
}