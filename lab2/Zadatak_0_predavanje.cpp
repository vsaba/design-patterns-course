#include <iostream>
#include <assert.h>
#include <stdlib.h>

using namespace std;

class Point
{
private:
    int x;
    int y;

public:
    int getX()
    {
        return x;
    }

    void setX(int x)
    {
        this->x = x;
        return;
    }

    int getY()
    {
        return y;
    }

    void setY(int y)
    {
        this->y = y;
        return;
    }

    void translate(int x, int y)
    {
        this->x += x;
        this->y += y;
    }
};

class Shape
{
public:
    virtual void draw() = 0;

    virtual void move(int x, int y) = 0;
};

class Circle : public Shape
{
private:
    Point center_;
    double radius;

public:
    Circle()
    {
        this->center_.setX(1);
        this->center_.setY(1);
        this->radius = 5;
    }
    Circle(Point &center, double radius) : center_(center), radius(radius) {}

    void draw()
    {
        cout << "in drawCircle" << endl;
    }

    void move(int x, int y)
    {
        cout << "in moveCircle" << endl;
        this->center_.translate(x, y);
    }
};

class Square : public Shape
{
private:
    Point center_;
    double side_;

public:
    Square()
    {
        this->center_.setX(1);
        this->center_.setY(1);
        this->side_ = 5;
    }
    Square(Point &center, double side) : center_(center), side_(side) {}

    void draw()
    {
        cout << "in drawSqaure" << endl;
        return;
    }

    void move(int x, int y)
    {
        cout << "in moveSquare" << endl;
        this->center_.translate(x, y);

        return;
    }
};

class Rhomb : public Shape
{
private:
    Point center_;
    double side_;

public:
    Rhomb()
    {
        this->center_.setX(1);
        this->center_.setY(1);
        this->side_ = 5;
    }
    Rhomb(Point &center, double side) : center_(center), side_(side) {}

    void draw()
    {
        cout << "in drawRhomb" << endl;

        return;
    }

    void move(int x, int y)
    {
        cout << "in moveRhomb" << endl;
        this->center_.translate(x, y);

        return;
    }
};

void drawShapes(Shape **shapes, int n)
{
    for (int i = 0; i < n; i++)
    {
        shapes[i]->draw();
    }
}

void moveShapes(Shape **shape, int n, int x, int y)
{
    for (int i = 0; i < n; i++)
    {
        shape[i]->move(x, y);
    }
}

int main(int argc, char const *argv[])
{
    Shape *shapes[5];

    shapes[0] = new Circle;
    shapes[1] = new Square;
    shapes[2] = new Rhomb;
    shapes[3] = new Circle;
    shapes[4] = new Rhomb;

    drawShapes(shapes, 5);
    moveShapes(shapes, 5, 2, 2);

    return 0;
}
