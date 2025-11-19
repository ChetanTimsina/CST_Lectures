#include<stdio.h>
struct st
{
    int price,pages;

}book[3];
void main()
{
    int i;
    for(i=0;i<3;i++)
    {
        printf("Enter %d detials: ",i);
        scanf("%d %d",&book[i].pages, &book[i].price);
    }
    printf("Information\n\nPages\tPrice\n");
    for(i=0;i<3;i++)
    {
        
        printf("\n%d\t%d",book[i].pages, book[i].price);
    }
}
