class ParcelPoint {
private:
    double x;
    double y;

public:
    ParcelPoint(double xCoord, double yCoord) : x(xCoord), y(yCoord) {}

    double getXCoordinate() const {
        return x;
    }

    double getYCoordinate() const {
        return y;
    }

    bool operator==(const ParcelPoint& other) const {
        return x == other.x && y == other.y;
    }

    bool operator<(const ParcelPoint& other) const {
        return x < other.x || (x == other.x && y < other.y);
    }
};