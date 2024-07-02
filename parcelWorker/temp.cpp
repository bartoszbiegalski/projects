#include <iostream>
#include <pugixml.hpp>
#include <fstream>
#include <sstream>
#include <map>
#include <vector>
#include <iomanip>
#include "Point.cpp"

int main() {
    std::map<Point, std::vector<std::string>> points;
    pugi::xml_document doc;

    for (int k = 1; k <= 1; k++) {
        std::string fileName = "gml/" +  std::to_string(k) + ".gml";
        std::cout<<fileName<<std::endl;
        pugi::xml_parse_result result = doc.load_file("namyslow.gml");
        if (!result) {
            std::cerr << "Error loading XML file: " << result.description() << std::endl;
            return 1;
        }
        pugi::xpath_node_set featureMembers = doc.select_nodes("//ogr:featureMember");
        for (pugi::xpath_node featureMember: featureMembers) {
            pugi::xml_node root = featureMember.node().child("ogr:namyslow").child("ogr:geometryProperty").child(
                    "gml:Polygon");

            std::string parcelName = featureMember.node().child("ogr:namyslow").child("ogr:ID_DZIALKI").text().get();
            for (pugi::xml_node child: root.children()) {
                std::string posList = child.child("gml:LinearRing").child("gml:posList").text().get();
                std::istringstream iss(posList);
                double x, y;

                iss >> x >> y;

                while (iss >> x >> y) {
                    Point newPoint(x, y);
                    points[newPoint].push_back(parcelName);
                }
            }
        }
    }

    std::ofstream zapis("wyniki.txt");

    zapis << std::fixed << std::setprecision(2);

    for (const auto& pair : points) {
        if (pair.second.size() > 1) {
            zapis << pair.first.x << " " << pair.first.y << " ";
            for (int k = 0; k < pair.second.size(); k++) {
                zapis<<pair.second.at(k)<<" ";
            }
            zapis<<std::endl;
        }

    }


}
