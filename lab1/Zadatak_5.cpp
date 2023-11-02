#include <iostream>

using namespace std;

class B
{
public:
    virtual int __cdecl prva() = 0;
    virtual int __cdecl druga(int) = 0;
};

class D : public B
{
public:
    virtual int __cdecl prva() { return 42; }
    virtual int __cdecl druga(int x) { return prva() + x; }
};

typedef void const (*PTRFUN)();

int main(int argc, char const *argv[])
{
    D *d = new D();
    PTRFUN *vtable = *(PTRFUN **)d;

    cout << "Prva: " << ((int (*)(B *))vtable[0])(d) << endl;
    cout << "Druga " << ((int (*)(B *, int))vtable[1])(d, 13) << endl;

    return 0;
}
