#include <pugixml.hpp>
#include <iostream>
#include <ostream>

class PowiatParser{
private:
    const pugi::xml_document &root;

public:
    PowiatParser(const pugi::xml_document &root) : root(root) {}

    void parse() {
        std::cout<<root<<std::endl;

        pugi::xml_node featureCollection = root.child("FeatureCollection");

        if (!featureCollection) {
            std::cerr << "No <FeatureCollection> element found." << std::endl;
        }

        for (pugi::xml_node featureMember = featureCollection.child("featureMember"); featureMember; featureMember = featureMember.next_sibling("featureMember")) {
            std::cout << "Found <featureMember> element: " << featureMember.name() << std::endl;

            for (pugi::xml_node child = featureMember.first_child(); child; child = child.next_sibling()) {
                std::cout << "  Child name: " << child.name() << ", value: " << child.child_value() << std::endl;
            }
        }
    }

    pugi::xml_node findNodeByPath(const pugi::xml_node &node, const std::string &path) {
        pugi::xml_node current = node;
        std::istringstream iss(path);
        std::string label;

        while (std::getline(iss, label, '/')) {
            current = current.child(label.c_str());
            if (!current) {
                return pugi::xml_node();
            }
        }
        return current;
    }
};