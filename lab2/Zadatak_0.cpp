#include <iostream>
#include <assert.h>
#include <stdlib.h>

using namespace std;

struct Point
{
    int x;
    int y;
};

struct Shape
{
    enum EType
    {
        circle,
        square,
        rhomb
    };
    EType type_;
};

struct Circle
{
    Shape::EType type_;
    double radius_;
    Point center_;
};
struct Square
{
    Shape::EType type_;
    double side_;
    Point center_;
};

struct Rhomb
{
    Shape::EType type_;
    double side_;
    Point center_;
};

void drawSquare(struct Square *)
{
    cerr << "in drawSquare\n";
}

void drawCircle(struct Circle *)
{
    cerr << "in drawCircle\n";
}

void drawRhomb(struct Rhomb *)
{
    cerr << "in drawRhomb" << endl;
}

void drawShapes(Shape **shapes, int n)
{
    for (int i = 0; i < n; ++i)
    {
        struct Shape *s = shapes[i];
        switch (s->type_)
        {
        case Shape::square:
            drawSquare((struct Square *)s);
            break;
        case Shape::circle:
            drawCircle((struct Circle *)s);
            break;
        case Shape::rhomb:
            drawRhomb((struct Rhomb *)s);
            break;
        default:
            assert(0);
            exit(0);
        }
    }
}

void moveCircle(Circle *circle, int x, int y)
{

    circle->center_.x += x;
    circle->center_.y += y;
    cout << "New circle position: (" << circle->center_.x << ", " << circle->center_.y << ")" << endl;

    return;
}

void moveSquare(Square *square, int x, int y)
{

    square->center_.x += x;
    square->center_.y += y;
    cout << "New square position: (" << square->center_.x << ", " << square->center_.y << ")" << endl;

    return;
}

void moveRhomb(Rhomb *rhomb, int x, int y)
{
    rhomb->center_.x += x;
    rhomb->center_.y += y;
    cout << "New rhomb position: (" << rhomb->center_.x << ", " << rhomb->center_.y << ")" << endl;

    return;
}

void moveShapes(Shape **shapes, int n, int x, int y)
{
    for (int i = 0; i < n; ++i)
    {
        struct Shape *s = shapes[i];
        switch (s->type_)
        {
        case Shape::square:
            moveSquare((struct Square *)s, x, y);
            break;
        case Shape::circle:
            moveCircle((struct Circle *)s, x, y);
            break;
        case Shape::rhomb:
            moveRhomb((struct Rhomb *)s, x, y);
            break;
        default:
            assert(0);
            exit(0);
        }
    }

    return;
}

int main()
{
    Shape *shapes[5];

    shapes[0] = (Shape *)new Circle;
    shapes[0]->type_ = Shape::circle;

    shapes[1] = (Shape *)new Square;
    shapes[1]->type_ = Shape::square;

    shapes[2] = (Shape *)new Square;
    shapes[2]->type_ = Shape::square;

    shapes[3] = (Shape *)new Circle;
    shapes[3]->type_ = Shape::circle;

    shapes[4] = (Shape *)new Rhomb;
    shapes[4]->type_ = Shape::rhomb;

    drawShapes(shapes, 5);

    /////

    Square *square = new Square;
    square->type_ = Shape::square;
    square->center_.x = 5;
    square->center_.y = 5;

    Circle *circle = new Circle;
    circle->type_ = Shape::circle;
    circle->center_.x = 5;
    circle->center_.y = 5;

    Rhomb *rhomb = new Rhomb;
    rhomb->type_ = Shape::rhomb;
    rhomb->center_.x = 5;
    rhomb->center_.y = 5;

    Shape *move_shapes[3];
    move_shapes[0] = (Shape *)square;
    move_shapes[1] = (Shape *)circle;
    move_shapes[2] = (Shape *)rhomb;

    moveShapes(move_shapes, 3, 5, 5);
}