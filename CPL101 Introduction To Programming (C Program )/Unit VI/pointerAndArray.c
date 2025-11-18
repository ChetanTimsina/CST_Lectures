/*Use of pointer in Array*/
#include<stdio.h>
void main()
{
    int a[5],i,*p;
    printf("REad array values:\n");
    for(i=0;i<5;i++)
    {
        scanf("%d",&a[i]);
    }
    printf("\nPrint Array Elements...\n");
     for(i=0;i<5;i++)
    {
        printf("%3d",a[i]);
    }
    p=&a[2];//pointer pointing to base address
    printf("\nValue\tMemory Address\n");
    printf("%d\t%d",*p,p);
    p++;
    printf("\n%d\t%d",*p,p);
    p++;
    printf("\n%d\t%d",*p,p);
}