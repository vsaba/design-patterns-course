#include <stdio.h>
#include <string.h>

const void *mymax(
    const void *base, size_t nmemb, size_t size,
    int (*compar)(const void *, const void *))
{
    void *maxValue;
    void *currValue;
    int max;
    for (int i = 0; i < nmemb; i++)
    {
        currValue = (char *)base + (i * size);
        maxValue = (char *)base + (max * size);

        if ((*compar)(currValue, maxValue) == 1)
        {
            max = i;
        }
    }

    return (char *)base + (max * size);
}

int gt_int(const void *first, const void *second)
{
    int *firstInt = (int *)first;
    int *secondInt = (int *)second;
    if (*firstInt > *secondInt)
    {
        return 1;
    }

    return 0;
}

int gt_char(const void *first, const void *second)
{

    char *firstChar = (char *)first;
    char *secondChar = (char *)second;
    if (*firstChar > *secondChar)
    {
        return 1;
    }

    return 0;
}

int gt_str(const void *first, const void *second)
{
    char **firstString = (char **)first;
    char **secondString = (char **)second;
    if (strcmp(*firstString, *secondString) > 0)
    {
        return 1;
    }

    return 0;
}

int main(int argc, char const *argv[])
{
    int arr_int[] = {1, 3, 5, 7, 4, 6, 9, 2, 0};
    char arr_char[] = "Suncana strana ulice";
    const char *arr_str[] = {
        "Gle", "malu", "vocku", "poslije", "kise",
        "Puna", "je", "kapi", "pa", "ih", "njise"};

    const int *max_int = mymax(arr_int, 9, sizeof(int), gt_int);
    const char *max_char = mymax(arr_char, 20, sizeof(char), gt_char);
    const char **max_str = mymax(arr_str, 11, sizeof(char *), gt_str);

    printf("Max int: %d\n", *max_int);
    printf("Max char: %c\n", *max_char);
    printf("Max string: %s\n", *max_str);

    return 0;
}
