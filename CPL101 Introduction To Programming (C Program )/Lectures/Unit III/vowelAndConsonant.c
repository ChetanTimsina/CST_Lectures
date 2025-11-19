#include<stdio.h>
void main()
{
    char ch;
    printf("Enter character: ");
    scanf("%c",&ch);//I

    switch(ch)//switch(I)
    {
        case 'A':  
        case 'a':   
        case 'E':
        case 'e':
        case 'I':
        case 'i':
        case 'o':
        case 'O':
        case 'u':
        case 'U':printf("Vowel");break;

        default: printf("Cosonant");break;
    }






}