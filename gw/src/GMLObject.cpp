#ifndef GMLOBJECT_CPP
#define GMLOBJECT_CPP

/**
 * @file GMLObject.cpp
 * @class GMLObject
 * @brief Class representing definitions of gml document with name, namespaces and
 * pointers to gml tree parts. Very likely to take a lot of memory.
 * @author Bartosz Biegalski
 */

#include <iostream>
#include "GMLObject.hpp"

GMLObject::GMLObject(xmlpp::Element *xmlRoot)
    : xmlRoot(xmlRoot)
{
}

void GMLObject::printObjectInfo()
{
    std::cout << "Object name: " << this->getName() << std::endl;
    std::cout << "Number of namespaces: " << this->getNamespaceMap().size() << std::endl;
}

// WARNING - VERY BAD AND AWFUL FUNCTION - TO BE REFACTORED

std::map<std::string, std::vector<xmlpp::Element *>> GMLObject::calculatePrefixMap()
{
    std::map<std::string, std::vector<xmlpp::Element *>> prefixMap;
    for (auto i : this->getNamespaceMap())
    {
        prefixMap.insert(std::make_pair(i.first, std::vector<xmlpp::Element *>()));
    }
    xmlpp::Node *firstLevelNode = this->xmlRoot->get_first_child();

    while (firstLevelNode)
    {
        xmlpp::Node *secondLevelNode = firstLevelNode->get_first_child();

        while (secondLevelNode)
        {
            xmlpp::Element *element = dynamic_cast<xmlpp::Element *>(secondLevelNode);
            if (element)
            {
                prefixMap[element->get_namespace_prefix()].push_back(element);
            }

            secondLevelNode = secondLevelNode->get_next_sibling();
        }

        firstLevelNode = firstLevelNode->get_next_sibling();
    }
    return prefixMap;
}

#endif