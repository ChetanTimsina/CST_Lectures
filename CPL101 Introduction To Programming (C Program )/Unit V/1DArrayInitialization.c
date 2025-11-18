#include<stdio.h>
void main()
{
    float a1[]={1.1, 1.2, 1.3, 2.2, 2.5, 4.4, 5.5};//compile time initialization of 1D Array
    int a2[5],i;
    printf("Enter array vlaues:\n");
    for(i=0;i<5;i++)
    {
        scanf("%d",&a2[i]); //run-time initialization
    }
    printf("\nArray values of array a1 are: ");
    for(i=0;i<sizeof(a1)/sizeof(a1[0]);i++)
    {
        printf("%f ",a1[i]);
    }
    printf("\nArray values of a2 are: ");
    for(i=0;i<5;i++)
    {
        printf("%d ",a2[i]);
    }
}