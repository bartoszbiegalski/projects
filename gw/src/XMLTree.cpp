/**
 * @file XMLTree.hpp
 * @class XMLTree
 * @brief Class representing object of xml-tree with prefix and schema location
 * @author Bartosz Biegalski
 */

#include "XMLTree.hpp"
#include "Typedef.hpp"

void XMLTree::addChild(xmlpp::Element *child)
{
    if (child)
    {
        this->children.push_back(child);
    }
}