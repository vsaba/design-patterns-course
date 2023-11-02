#include <iostream>
#include <vector>
#include <set>
#include <string.h>

using namespace std;

template <typename Iterator, typename Predicate>
Iterator mymax(
    Iterator first, Iterator last, Predicate pred)
{

    if (first == last)
    {
        return first;
    }

    Iterator maxValue = first;
    first++;

    for (; first != last; first++)
    {
        if (pred(&(*first), &(*maxValue)))
        {
            maxValue = first;
        }
    }

    return maxValue;
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
    const char *arr_str[] = {
        "Gle", "malu", "vocku", "poslije", "kise",
        "Puna", "je", "kapi", "pa", "ih", "njise"};

    vector<int> vec = {1, 3, 5, 7, 4, 6, 10, 2, 0};
    set<int> set = {1, 3, 5, 7, 4, 6, 11, 2, 0};

    int *maxInt = mymax(&arr_int[0], &arr_int[8], gt_int);
    const char **maxStr = mymax(&arr_str[0], &arr_str[10], gt_str);
    auto maxInt_vector = mymax(vec.begin(), vec.end(), gt_int);
    auto max_set = mymax(set.begin(), set.end(), gt_int);

    cout << "Max int from array: " << *maxInt << endl;
    cout << "Max string from array: " << *maxStr << endl;
    cout << "Max int from vector: " << *maxInt_vector << endl;
    cout << "Max int from set: " << *max_set << endl;

    return 0;
}
