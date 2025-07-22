#ifndef XSDVALIDATOR_HPP
#define XSDVALIDATOR_HPP

/**
 * @file XSDValidator.hpp
 * @class XSDValidator
 * @brief Declarations of XSDValidator class. Takes map of namespaces, searches
 * for .xsd files and parses them
 * @author Bartosz Biegalski
 */

#include "GMLObject.hpp"
#include "Typedef.hpp"
#include <libxml/parser.h>
#include <libxml/xmlschemas.h>

class XSDValidator
{
public:
    void validateNamespaces(GMLObject *gmlObject);

private:
    struct SchemaContext
    {
        xmlSchemaPtr schema;
        xmlSchemaValidCtxtPtr validCtxt;
    };

    std::map<std::string, SchemaContext> validatorMap;

    void traverseAndValidate(xmlNodePtr node);
    void validateSubtree(xmlNodePtr node, xmlSchemaValidCtxtPtr validCtxt);
};
#endif // XSDVALIDATOR_HPP
