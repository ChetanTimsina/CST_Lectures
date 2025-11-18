#include<stdio.h>
#define N 6
int f1(int x,int y);
void f2(int z[N]);
void main()
{
    int arr[N],i,prod;
    printf("\nRead array values:\n");
    for(i=0;i<N;i++)
    {
        scanf("%d",&arr[i]);
    }
    printf("\nThe array values are:\n");
    for(i=0;i<N;i++)
    {
        printf("%3d",arr[i]);
    }
    prod=f1(arr[0],arr[2]);//function call to pass array element
    printf("\nThe product is %d",prod);
    f2(arr);//function call to pass array
}
int f1(int x,int y)
{
    return x*y;
}
void f2(int z[N])
{
    int i,sum=0;
    for(i=0;i<N;i++)
    {
        sum=sum+z[i];
    }
    printf("\nThe sum is:%d",sum);
}