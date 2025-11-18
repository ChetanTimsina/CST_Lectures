#include<stdio.h>
void main()
{
    printf("\nAddition of two matrices\n");
    printf("\n****************************");

    int m1[2][2],m2[2][2],r[2][2],i,j;
    printf("\nREad first matrix values:\n");
    for(i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
            scanf("%d",&m1[i][j]);
        }
    }
    printf("\nREad second matrix values:\n");
    for(i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
            scanf("%d",&m2[i][j]);
        }
    }
    printf("\nMatrix m1 values are:\n");
    for(i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
            printf("%2d",m1[i][j]);
        }
        printf("\n");
    }
    printf("\nMatrix values of m2 are:\n");
    for(i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
            printf("%2d",m2[i][j]);
        }
        printf("\n");
    }
    //printf("\nREad first matrix values:\n");
    for(i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
           r[i][j]=m1[i][j]+m2[i][j];
        }
    }
    printf("\nAdded matrix values are:\n");
    for(i=0;i<2;i++)
    {
        for(j=0;j<2;j++)
        {
            printf("%2d",r[i][j]);
        }
        printf("\n");
    }
}