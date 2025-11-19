#include<stdio.h>
void main()
{
    int a,b,c,*p1,*p2,*p3,sum;
    p1=&a;
    p2=&b;
    p3=&c;
    printf("REad three values: ");
    scanf("%d %d %d",p1,p2,p3);
    
    sum=(*p1+*p2+*p3);
    printf("\nThe sum is: %d",sum);

}
