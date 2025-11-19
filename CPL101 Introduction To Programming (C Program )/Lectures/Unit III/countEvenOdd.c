#include<stdio.h>
void main()
{
    int cE=0,cO=0,i;
    for(i=1;i<26;i++)
    {
        if(i%2==1)
        {
            cO=cO+1;
        }
        else
        {
            cE=cE+1;

        }
       

    }
    printf("Total event count: %d",cE);
    printf("\nTotal odd count: %d",cO);

}