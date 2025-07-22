#ifndef GMLOBJECT_HPP
#define GMLOBJECT_HPP

/**
 * @file GMLObject.hpp
 * @class GMLObject
 * @brief Class representing declarations of gml document with name, namespaces and
 * pointers to gml tree parts. Very likely to take a lot of memory.
 * @author Bartosz Biegalski
 */

#include "Typedef.hpp"

#include "XMLTree.hpp"
#include <libxml/tree.h> // dla xmlDocPtr

class GMLObject
{
private:
    std::string name;
    NamespaceMap namespaceMap;
    xmlpp::Element *xmlRoot;
    xmlpp::Document *xmlDocument;

    std::map<std::string, std::vector<xmlpp::Element *>> prefixMap;

    xmlDocPtr rawDoc = nullptr; // ← nowy wskaźnik do libxml2 dokumentu

public:
    GMLObject(xmlpp::Element *xmlRoot);

    std::string getName() const { return this->name; }
    void setName(const std::string &name) { this->name = name; }

    NamespaceMap &getNamespaceMap() { return this->namespaceMap; }
    void setNamespaceMap(const NamespaceMap &namespaceMap) { this->namespaceMap = namespaceMap; }

    xmlpp::Element *getXMLRoot() const { return this->xmlRoot; }
    void setXMLRoot(xmlpp::Element *xmlRoot) { this->xmlRoot = xmlRoot; }

    xmlpp::Document *getXMLDocument() const { return this->xmlDocument; }
    void setXMLDocument(xmlpp::Document *xmlDocument) { this->xmlDocument = xmlDocument; }

    std::map<std::string, std::vector<xmlpp::Element *>> getPrefixMap() const { return this->prefixMap; }
    void setPrefixMap(std::map<std::string, std::vector<xmlpp::Element *>> prefixMap) { this->prefixMap = prefixMap; }

    xmlDocPtr getRawDoc() const { return this->rawDoc; }
    void setRawDoc(xmlDocPtr rawDoc) { this->rawDoc = rawDoc; }

    std::map<std::string, std::vector<xmlpp::Element *>> calculatePrefixMap(/*TOODO: criteria*/);

    void printObjectInfo();
};

#endif