#include <stdio.h>
#include <malloc.h>
#include <stdbool.h>

typedef double (*PTRFUN)();

// Unary
struct Unary_Function
{
    PTRFUN *vtable;
    int lower_bound;
    int upper_bound;
} Unary_Function;

double negative_value_at(struct Unary_Function *function, double x)
{
    return -(function->vtable[0](function, x));
}

PTRFUN unaryFunc[2] = {NULL, negative_value_at};

void constructUnary(struct Unary_Function *func, int lower_bound, int upper_bound)
{
    func->vtable = unaryFunc;
    func->lower_bound = lower_bound;
    func->upper_bound = upper_bound;

    return;
}

struct Unary_Function *createUnary(int lower_bound, int upper_bound)
{
    struct Unary_Function *func = malloc(sizeof(struct Unary_Function));
    constructUnary(func, lower_bound, upper_bound);
    return func;
}

void tabulate(struct Unary_Function *func)
{
    for (int x = func->lower_bound; x <= func->upper_bound; x++)
    {
        printf("f(%d)=%lf\n", x, func->vtable[0](func, (double)x));
    }

    return;
}

bool same_functions_for_ints(struct Unary_Function *func1, struct Unary_Function *func2, double tolerance)
{
    if (func1->lower_bound != func2->lower_bound)
    {
        return false;
    }
    if (func1->upper_bound != func2->upper_bound)
    {
        return false;
    }
    for (int x = func1->lower_bound; x <= func1->upper_bound; x++)
    {
        double delta = func1->vtable[0](func1, x) - func2->vtable[0](func2, x);
        if (delta < 0)
        {
            delta = -delta;
        }
        if (delta > tolerance)
        {
            return false;
        }
    }
    return true;
}

// Square

struct Square
{
    PTRFUN *vtable;
    int lower_bound;
    int upper_bound;
} Square;

double square_value_at(struct Square *func, double x)
{
    return x * x;
}

PTRFUN squareFunc[2] = {square_value_at, negative_value_at};

void constructSquare(struct Square *square, int lower_bound, int upper_bound)
{
    square->vtable = squareFunc;
    square->lower_bound = lower_bound;
    square->upper_bound = upper_bound;
    return;
}

struct Square *createSquare(int lower_bound, int upper_bound)
{
    struct Square *square = malloc(sizeof(struct Square));
    constructSquare(square, lower_bound, upper_bound);
    return square;
}

// Linear

struct Linear
{
    PTRFUN *vtable;
    int lower_bound;
    int upper_bound;
    int a;
    int b;

} Linear;

double linear_value_at(struct Linear *func, double x)
{
    return func->a * x + func->b;
}

PTRFUN linearFunc[2] = {linear_value_at, negative_value_at};

void constructLinear(struct Linear *linear, int lower_bound, int upper_bound, int a, int b)
{
    linear->vtable = linearFunc;
    linear->lower_bound = lower_bound;
    linear->upper_bound = upper_bound;
    linear->a = a;
    linear->b = b;

    return;
}

struct Linear *createLinear(int lower_bound, int upper_bound, int a, int b)
{
    struct Linear *linear = malloc(sizeof(struct Linear));
    constructLinear(linear, lower_bound, upper_bound, a, b);

    return linear;
}

int main(int argc, char const *argv[])
{
    struct Unary_Function *f1 = createSquare(-2, 2);
    tabulate(f1);
    struct Unary_Function *f2 = createLinear(-2, 2, 5, -2);
    tabulate(f2);
    printf("f1==f2: %s\n", same_functions_for_ints(f1, f2, 1E-6) ? "DA" : "NE");
    printf("neg_val f2(1) = %lf\n", f2->vtable[1](f2, 1.0));
    free(f1);
    free(f2);
    return 0;
}
