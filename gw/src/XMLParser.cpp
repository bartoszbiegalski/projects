#ifndef XMLPARSER_CPP
#define XMLPARSER_CPP

/**
 * @file XMLParser.cpp
 * @class XMLParser
 * @brief Definitions of XMLParser class. Takes XML file and parses it
 * @author Bartosz Biegalski
 */

#include "XMLParser.hpp"
#include <stdexcept>

xmlpp::DomParser XMLParser::parser;

void XMLParser::parseGMLObject(GMLObject *gmlObject)
{
    // libxml++ parsing
    parser.parse_file(gmlObject->getName());

    if (!parser)
    {
        throw std::runtime_error("Error when parsing XML file: " + gmlObject->getName());
    }

    gmlObject->setXMLDocument(parser.get_document());

    xmlDocPtr rawDoc = xmlReadFile(gmlObject->getName().c_str(), NULL, 0);
    if (!rawDoc)
    {
        throw std::runtime_error("libxml2 failed to parse file: " + gmlObject->getName());
    }

    gmlObject->setRawDoc(rawDoc);
}

void XMLParser::setXMLRoot(GMLObject *gmlObject)
{
    gmlObject->setXMLRoot(parser.get_document()->get_root_node());
}

#endif // XMLPARSER_CPP