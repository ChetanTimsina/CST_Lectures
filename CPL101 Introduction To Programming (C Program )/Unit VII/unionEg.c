#include<stdio.h>
struct s
{
    int age;
    float m;
};
union u
{
    int age;
    float m;
};

int main()
{
    struct s st;
    union u un;
    st.age=23;
    st.m=56;
    printf("\nEnter age: ");
    scanf("%d",&un.age);
    printf("\nEnter marks: ");
    scanf("%f",&un.m);
    printf("\nSize of structure: %d",sizeof(st));
    printf("\nSize of union: %d",sizeof(un));
    printf("\nAge\tMark\n");
    printf("%d\t%f",un.age,un.m);
    printf("\n%d\t%f",st.age,st.m);

    return 0;
}