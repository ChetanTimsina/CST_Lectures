#include<stdio.h>
#include<stdlib.h>
int main()
{
    FILE *pt;//File pointer
    int age;
    float m;
    char name[30];
    pt=fopen("myfileOp.bin","wb+"); //creating a file
    if(pt==NULL)
    {
        printf("\nError in opening a file");
        exit(1);
    }
    printf("\nWhat is your name?");
    gets(name);
    printf("\nWhat is your age?");
    scanf("%d",&age);
    printf("\nWhat is your score in CPL101?");
    scanf("%f",&m);
    /*Writing into a file*/
    fprintf(pt,"\nName: ");
    fputs(name,pt);
    fprintf(pt,"Age:%d",age);
    fprintf(pt,"\nMark:%f",m);
    /*Reading from a file*/
    fscanf(pt,"%d",age);
    printf("Age is: %d",age);
    fclose(pt); //closing a file 
    return 0;
}