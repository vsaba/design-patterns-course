#include <iostream>

using namespace std;

class CoolClass
{
public:
    virtual void set(int x) { x_ = x; };
    virtual int get() { return x_; };

private:
    int x_;
};
class PlainOldClass
{
public:
    void set(int x) { x_ = x; };
    int get() { return x_; };

private:
    int x_;
};

int main(int argc, char const *argv[])
{
    cout << "Velicina PlainOldClass: " << sizeof(PlainOldClass) << endl;
    cout << "Velicina CoolClass: " << sizeof(CoolClass) << endl;
    return 0;
}
