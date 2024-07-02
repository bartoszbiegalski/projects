#ifndef POINT_H
#define POINT_H

#include <functional>
#include <vector>

struct Point {
    double x, y;
    std::string name;
    std::vector<std::string> dzialki;
    std::vector<Point> neighbours;

    bool operator==(const Point& other) const {
        return x == other.x && y == other.y;
    }

    friend std::ostream& operator<<(std::ostream& os, const Point& b) {
        os << "(" << b.x << ", " << b.y << ")";
        return os;
    }

    Point(const double x, const double y, const std::string name) : x(x), y(y), name(name) {}
};

namespace std {
    template <>
    struct hash<Point> {
        size_t operator()(const Point& p) const {
            size_t h1 = std::hash<double>{}(p.x);
            size_t h2 = std::hash<double>{}(p.y);
            return h1 ^ (h2 << 1);
        }
    };
}

#endif // POINT_H
