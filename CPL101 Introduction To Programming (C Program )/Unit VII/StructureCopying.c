#include<stdio.h>
struct book
{
    char title[40];
    int price;
};
void main()
{
    struct book b1,b2;
    printf("Enter detials of books:\n");
    printf("\nenter the name of book1: ");
    gets(b1.title);
    printf("\nEnter the price of book 1");
    scanf("%d",&b1.price);
    b2=b1;
    printf("\nDetials of the book\n****************\n");
    printf("\nName\t Price\n");
    printf("%s\t%d",b1.title,b1.price);
    printf("\n%s\t%d",b2.title,b2.price);
    if(b1.price==b2.price)
    {
        printf("\nThe price is equal");
    }
    else{
        printf("\nThe price is not equal");
    }
}