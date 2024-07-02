#include <vector>
#include <boost/geometry.hpp>
#include <boost/geometry/index/rtree.hpp>

namespace bg = boost::geometry;
namespace bgi = boost::geometry::index;

typedef bg::model::point<double, 2, bg::cs::cartesian> point;
typedef std::pair<point, unsigned> value;
typedef bgi::rtree<value, bgi::quadratic<16>> rtree_t;

class KDTree {
private:
    rtree_t tree;

public:
    KDTree() {}

    void addPoint(const point& p) {
        tree.insert(std::make_pair(p, tree.size()));
    }

    std::vector<value> searchInRadius(const point& query, double minDist, double maxDist) const {
        std::vector<value> result;
        tree.query(bgi::satisfies([&query, minDist, maxDist](const value& v) {
            double distance = bg::distance(query, v.first);
            return distance >= minDist && distance <= maxDist;
        }), std::back_inserter(result));
        return result;
    }
};