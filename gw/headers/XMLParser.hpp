#ifndef XMLPARSER_HPP
#define XMLPARSER_HPP

/**
 * @file XMLParser.hpp
 * @class XMLParser
 * @brief Declarations of XMLParser class. Takes XML file and parses it
 * @author Bartosz Biegalski
 */

#include <libxml++/libxml++.h>
#include "GMLObject.hpp"

class XMLParser
{
private:
    static xmlpp::DomParser parser;

    XMLParser() = delete;

public:
    static void parseGMLObject(GMLObject *gmlObject);
    static void setXMLRoot(GMLObject *gmlObject);
};

#endif // XMLPARSER_HPP