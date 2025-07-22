#ifndef XMLTREE_HPP
#define XMLTREE_HPP

/**
 * @file .hpp
 * @class
 * @brief Class representing object of xml-tree with prefix and schema location
 * @author Bartosz Biegalski
 */

#include "Typedef.hpp"

class XMLTree
{
private:
    std::string prefix;
    std::string schemaLocation;
    std::vector<xmlpp::Element *> children;

public:
    XMLTree(const std::string &prefix = "",
            const std::string &schemaLocation = "")
        : prefix(prefix), schemaLocation(schemaLocation) {};

    std::string getPrefix() const { return this->prefix; }
    void setPrefix(const std::string &prefix) { this->prefix = prefix; }

    std::string getSchemaLocation() const { return this->schemaLocation; }
    void setSchemaLocation(const std::string &schemaLocation) { this->schemaLocation = schemaLocation; }

    void addChild(xmlpp::Element *child);
};

#endif // XMLTREE_HPP