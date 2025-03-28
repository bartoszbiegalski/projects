/**
 * @file XMLDocument.cpp
 * @class XMLDocument.cpp
 * @brief Contains methods for loading and uploading xml structure
 * @author Bartosz Biegalski
 * @date 28.03.2025
 */
#include <iostream>
#include "file_handling/XMLDocument.hpp"

XMLDocument::XMLDocument(XMLRoot* xmlRoot)
    : xmlRoot(xmlRoot) 
{
}
std::string XMLDocument::getDocumentName() const
{
    return this->documentName;
}
int XMLDocument::getDocumentId() const
{
    return this->documentId;
}
NamespaceMap &XMLDocument::getNamespaceMap() 
{
    return this->namespaceMap;
}
XMLRoot* XMLDocument::getXmlRoot() const
{
    return this->xmlRoot;
}
void XMLDocument::setDocumentName(const std::string &name)
{
    documentName = name;
}
void XMLDocument::setDocumentId(int id)
{
    documentId = id;
}
void XMLDocument::setNamespaceMap(const NamespaceMap &nsMap)
{
    namespaceMap = nsMap;
}

void XMLDocument::setXmlRoot(XMLRoot* root)
{
    xmlRoot = root; 
}


void XMLDocument::printXMLDocument() const
{
    std::cout << "Document name : " << documentName << std::endl;
    std::cout << "Document namespaces :";
    for (auto &ns : namespaceMap)
    {
        std::cout << ns.first << " " << ns.second << std::endl;
    }
}
