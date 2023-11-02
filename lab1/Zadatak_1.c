#include <stdio.h>
#include <malloc.h>

typedef char const *(*PTRFUN)();

struct Animal
{
    PTRFUN *vtable;
    char *name;
} Animal;

char const *dogGreet()
{
    return "vau!";
}

char const *dogMenu()
{
    return "kuhanu govedinu";
}

char const *catGreet()
{
    return "mijau!";
}

char const *catMenu()
{
    return "konzerviranu tunjevinu";
}

PTRFUN dogFunc[2] = {dogGreet, dogMenu};
PTRFUN catFunc[2] = {catGreet, catMenu};

void animalPrintGreeting(struct Animal *animal)
{
    printf("%s pozdravlja: %s\n", animal->name, animal->vtable[0]());
    return;
}

void animalPrintMenu(struct Animal *animal)
{
    printf("%s voli %s\n", animal->name, animal->vtable[1]());
    return;
}

void constructDog(struct Animal *dog, char *name)
{
    dog->vtable = dogFunc;
    dog->name = name;
    return;
}

void constructCat(struct Animal *cat, char *name)
{
    cat->vtable = catFunc;
    cat->name = name;
    return;
}

struct Animal *createDog(char *name)
{
    struct Animal *dog = malloc(sizeof(struct Animal));
    constructDog(dog, name);
    return dog;
}

struct Animal *createCat(char *name)
{
    struct Animal *cat = malloc(sizeof(struct Animal));
    constructCat(cat, name);
    return cat;
}

void stack()
{
    printf("Hello from the stack\n");
    struct Animal dog;
    dog.vtable = dogFunc;
    dog.name = "Ofelije";
    animalPrintGreeting(&dog);
    animalPrintMenu(&dog);
}

void heap()
{
    printf("Hello from the heap\n");
    struct Animal *cat = malloc(sizeof(struct Animal));
    cat->vtable = catFunc;
    cat->name = "Polonije";
    animalPrintGreeting(cat);
    animalPrintMenu(cat);
}

void testAnimals()
{
    struct Animal *p1 = createDog("Hamlet");
    struct Animal *p2 = createCat("Ofelija");
    struct Animal *p3 = createDog("Polonije");

    animalPrintGreeting(p1);
    animalPrintGreeting(p2);
    animalPrintGreeting(p3);

    animalPrintMenu(p1);
    animalPrintMenu(p2);
    animalPrintMenu(p3);

    free(p1);
    free(p2);
    free(p3);
}

int main(int argc, char const *argv[])
{
    testAnimals();
    printf("\n");
    stack();
    printf("\n");
    heap();
    return 0;
}
