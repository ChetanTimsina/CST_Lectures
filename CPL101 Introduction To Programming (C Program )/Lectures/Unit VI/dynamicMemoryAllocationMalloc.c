/*Dynamic Memory Allocation - calloc()*/
#include<stdio.h>
#include<stdlib.h>
void main()
{
    int N,*ptr,sum,avg,i;
    printf("\nREAd N: ");
    scanf("%d",&N);
    ptr=(int*)malloc(N*sizeof(int));
    if(ptr==NULL)
    {
        printf("Unsuccessful...");
        exit(0);
    }
    else
    {
        for(i=0;i<N;i++)
        {
            scanf("%d",(ptr+i));
            sum+=*(ptr+i);
        }
        printf("\nPrint the entered values:\t");
        for(i=0;i<N;i++)
        {
            printf("%2d",*(ptr+i));
        }

    }
    avg=sum/N;
    printf("\nSum:%d\nAverage:%d",sum,avg);
    free(ptr);

    


}