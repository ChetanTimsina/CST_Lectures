/*Example program on embedded nested Structure*/
#include<stdio.h>
struct salary
{
    char name[30];
    int basic;
    struct 
    {
        int TA;
        int HA;
    }all;
    
};
void main()
{
    struct salary emp;
    printf("Enter the name of employee: ");
    gets(emp.name);
    printf("\nEnter basic saslary: ");
    scanf("%d",&emp.basic);
    printf("\nEnter TA: ");
    scanf("%d",&emp.all.TA);
    printf("\nEnter the HA: ");
    scanf("%d",&emp.all.HA);
    printf("\nName\t\tBasic\tTA\tHA\n");
    printf("%s\t%d\t%d\t%d",emp.name,emp.basic,emp.all.TA,emp.all.HA);
}