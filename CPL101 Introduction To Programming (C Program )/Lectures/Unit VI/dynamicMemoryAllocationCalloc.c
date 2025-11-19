/*Dynamic Memory Allocation - calloc()*/
#include<stdio.h>
#include<stdlib.h>
void main()
{
    int N,*ptr,sum,avg,i;
    printf("\nREAd N: ");
    scanf("%d",&N);
    ptr=(int*)calloc(N,sizeof(int));
    if(ptr==NULL)
    {
        printf("Unsuccessful...");
        exit(0);
    }
    else
    {
        for(i=0;i<N;i++)
        {
            ptr[i]=i+2;
            printf("%2d",ptr[i]);
        }
    }
    free(ptr);

    


}