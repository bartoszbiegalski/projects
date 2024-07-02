#include <vector>
#include <string>
#include <iostream>
#include <set>
#include "ParcelPoint.cpp"

class ParcelEdge {
private:
    ParcelPoint start;
    ParcelPoint end;
    std::set<std::string> parcelId;

public:
    ParcelEdge(const ParcelPoint &newStart, const ParcelPoint &newEnd)
            : start(newStart), end(newEnd) {}

    ParcelPoint getStart() const {
        return start;
    }

    ParcelPoint getEnd() const {
        return end;
    }

    void pushParcelId(const std::string& newParcelId) {
        parcelId.insert(newParcelId);
    }

    bool operator<(const ParcelEdge& other) const {
        if (start == other.start && end == other.end) {
            return false;
        }
        if (start == other.end && end == other.start) {
            return false;
        }
        if (start < other.start || (start == other.start && end < other.end)) {
            return true;
        }
        if (start < other.end || (start == other.end && end < other.start)) {
            return true;
        }
        return false;
    }

    friend std::ostream& operator<<(std::ostream& os, const ParcelEdge& parcelEdge) {
        os << "(" << parcelEdge.start.getXCoordinate() << ", " << parcelEdge.start.getYCoordinate() << "), "
           << "(" << parcelEdge.end.getXCoordinate() << ", " << parcelEdge.end.getYCoordinate() << ")";
        return os;
    }
};