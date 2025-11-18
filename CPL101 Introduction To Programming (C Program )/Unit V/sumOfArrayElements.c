#include<stdio.h>
void main()
{
    int myArr[10],i,sum=0;
    printf("\nREad array values:\n");
    for(i=0;i<9;i++)
    {
        scanf("%d",&myArr[i]);
    }
    printf("\nArray values are:\n");
    for(i=0;i<10;i++)
    {
        printf("%d ",myArr[i]);
    }
    for(i=0;i<10;i++)
    {
        sum=sum+myArr[i];
    }
    printf("\nThe sum of array values are:%d",sum);

}