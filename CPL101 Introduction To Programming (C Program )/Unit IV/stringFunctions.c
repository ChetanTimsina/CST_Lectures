#include<stdio.h>
#include<string.h>
void funct(char x[40],char y[40])
{
    char z[]="CST",a[]="JNEC";
    int val;
    printf("\nLenght of my name is:%d",strlen(x));
    printf("\nLenght of friend's name is: %d",strlen(y));
    val=strcmp(x,y);
    printf("\nString comparison: %d",val);
    printf("\nThe string concatenation is: ",strcat(x,y));
    puts(x);
    printf("\nString Copy: ");
    puts(strcpy(a,z));
}
void main()
{
    char Mname[50],Fname[49];
    printf("Read your name: ");
    fgets(Mname,sizeof(Mname),stdin);
    printf("\nREad your friend's name:");
    fgets(Fname,sizeof(Fname),stdin); 
    funct(Mname,Fname);  
}