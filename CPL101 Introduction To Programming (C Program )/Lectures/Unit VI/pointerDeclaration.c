/*Pointer Declaration and Initialization*/
#include<stdio.h>
void main()
{
    int x=50,*pt;
    pt=&x;
    printf("\nMemory address of pointer: %d",&pt);
    printf("\nValue hold by pointer: %d",pt);
    printf("\nvalue pointed by pointer: %d",*pt);

}