#include<stdio.h>
struct book
{
    char title[40];
    int price;
}b1={"introduciton to c",1000};
void main()
{
    printf("\nDetials of the book\n****************\n");
    printf("\nName\t Price\n");
    printf("%s\t%d",b1.title,b1.price);
}