#include <iostream>
#include <pugixml.hpp>
#include <vector>
#include "PointVector.cpp"
#include <iomanip>
#include <set>
#include "ParcelEdge.cpp"


class PowiatReader {
private:
    std::string fileName;
    pugi::xml_document doc;
    pugi::xml_parse_result result;
    std::set<ParcelEdge> parcelEdges;
public:
    PowiatReader(const std::string &filename) : fileName(filename) {}

    void read() {
        int i = 0;

        result = doc.load_file(fileName.c_str());
        std::cout << std::setprecision(6) << std::fixed;
        if (!result) {
            std::cerr << "Error while loading XML file: " << result.description() << std::endl;
            return;
        }

        pugi::xpath_node_set featureMembers = doc.select_nodes("//*[local-name()='dzialki']");

        for (const auto& featureMemberNode : featureMembers) {
            pugi::xml_node featureMember = featureMemberNode.node();

            std::string parcelId = featureMemberNode.node().select_node("ms:NUMER_DZIALKI").node().text().get();

            pugi::xpath_node_set polygons = featureMember.select_nodes(".//*[local-name()='Polygon']");

            for (const auto& polygonNode : polygons) {
                pugi::xml_node polygon = polygonNode.node();

                pugi::xml_node posList = polygon.select_node(".//*[local-name()='posList']").node();

                if (posList) {
                    std::string posListValue = posList.child_value();
                    std::istringstream iss(posListValue);
                    std::string x, y;
                    iss >> x >> y;

                    double old_x = stod(x);
                    double old_y = stod(y);

                    while(iss >> x >> y) {
                        double new_x = stod(x);
                        double new_y = stod(y);

                        ParcelPoint start(old_x, old_y);
                        ParcelPoint end(new_x, new_y);

                        ParcelEdge edge(start, end);
                        //edge.pushParcelId(parcelId);

                        if (parcelEdges.count(edge) == 0) {
                            edge.pushParcelId(parcelId);
                            parcelEdges.insert(edge);
                        }
                        else {
                            parcelEdges.find(edge);
                        }

                        parcelEdges.insert(edge);

                        //std::cout<<edge<<std::endl;
                        i++;
                        old_x = new_x;
                        old_y = new_y;
                        //pointVector.push_back(Point(new_x, new_y));
                        //pointVector.addElement(Point(new_x, new_y, name));
                    }
                } else {
                    std::cerr << "Nie znaleziono etykiety <posList> wewnątrz <Polygon>." << std::endl;
                }
            }
            /*
            for (int i = 0; i < points.size() - 1; i++) {
                for (int j = i+1; j < points.size(); j++) {
                    if (points.at(i) == points.at(j)) {
                        std::cout<<points.at(i)<<" "<<points.at(j)<<std::endl;
                    }
                }
            }
             */
        }
        std::cout<<i<<std::endl;
        std::cout<<parcelEdges.size()<<std::endl;
        for (ParcelEdge parcelEdge : parcelEdges) {
            std::cout<<parcelEdge<<std::endl;
        }
    }

    void checkIntegrality() {
       // if (pointVector.size() == 0) std::cout<<"empty list\n";
      //  else return;
    }
};
