#include<stdio.h>
void add(); //function declaration
void main()
{
    add();//function call
    add();
    add();
    add();
}
void add() //function definition
{
    int a=4,b=6;
    printf("The sum is %d",(a+b));
    sub();
    sub();
    

}
void sub()
{
    printf("this is second function");
}