#include<stdio.h>
struct book
{
    char title[40];
    int price;
};
void main()
{
    struct book b1;
    printf("Enter detials of books:\n");
    printf("\nenter the name of book1: ");
    gets(b1.title);
    printf("\nEnter the price of book 1");
    scanf("%d",&b1.price);
    
    printf("\nDetails of the book\n****************\n");
    printf("\nName\t Price\n");
    printf("%s\t%d",b1.title,b1.price);
}