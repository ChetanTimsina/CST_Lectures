/*Pointer to a pointer Example program*/
#include<stdio.h>
void main()
{
    int x=9,*p,**pt;
    p=&x;
    pt=&p;
    printf("\nMemory addres of x:%d",&x);
    printf("\nValue assinged to p: %d",p);
    printf("\nMemory address of p:%d",&p);
    printf("\nValue hold by pt: %d",pt);
    printf("\nValue poninted by p:%d",*p);
    printf("\nValue pointed by pt: %d",**pt);
}