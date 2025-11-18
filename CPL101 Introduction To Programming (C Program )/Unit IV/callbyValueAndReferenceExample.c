#include<stdio.h>
void callMe(int A, int B);
void callMeToo(int *,int *);
void main()
{
    int a=3,b=4,c=8,d=99;
    printf("Value before\na:%d\tb:%d",a,b);
    callMe(a,b);//function call (call by value)
    printf("\nValue after\na:%d\tb:%d",a,b);

    printf("Value before\nc:%d\td:%d",c,d);
    callMeToo(&c,&d);//function call (call by reference)
    printf("Value after\nc:%d\td:%d",c,d);
}
void callMe(int A,int B)
{
    A=A+20;
    B=B+50;

}
void callMeToo(int *C, int *D)
{
    *C=*C+20;
    *D=*D+50;

}