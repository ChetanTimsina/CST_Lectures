#include<stdio.h>
void main()
{
    int a[2][2]={{1,2},{2,3}},x,y;//compile time initialization
    for(x=0;x<2;x++)
    {
        for(y=0;y<2;y++)
        {
            printf("%2d",a[x][y]);
        }
        printf("\n");
        //printf("\nsum of first row: %d",a[0][0]+a[0][1]);
    }

}