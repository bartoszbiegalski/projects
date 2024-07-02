#include <iostream>
#include <vector>
#include "Point.cpp"
#include "cmath"

class PointVector : public std::vector<Point> {
private:
    double distance(const Point &a, const Point &b) {
        return sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

public:

    PointVector() : std::vector<Point>() {}

    PointVector(std::initializer_list<Point> initList) : std::vector<Point>(initList) {}

    bool pointExists(const std::vector<Point>& points, const Point& point) {
        for (const auto& p : points) {
            if (p.x == point.x && p.y == point.y) {
                return true;
            }
        }
        return false;
    }

    void addElement(const Point& element) {
        if (pointExists(*this, element)) {

        }
        else {
            this->push_back(element);
        }
    }

    void checkIntegrality() {
        int k = 0;

        for (int i = 0; i < this->size() - 1; i++) {
            for (int j = i + 1; j < this->size(); j++) {
                if(distance(this->at(i), this->at(j)) > 0.0 && distance(this->at(i), this->at(j)) <= 0.15) {
                    std::cout<< this->at(i)<<" "<< this->at(j)<<" "<<distance(this->at(i), this->at(j))<<std::endl;

                    //std::cout<<"o kurcze\n";
                    k++;
                }
            }
        }
        std::cout<<k<<std::endl;
    }
};

