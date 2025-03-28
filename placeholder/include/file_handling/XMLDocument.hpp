#ifndef XMLDOCUMENT_HPP
#define XMLDOCUMENT_HPP

/**
 * @file XMLDocument.hpp
 * @class XMLDocument
 * @brief Contains structure of loaded XML with namespaces and XML tree
 * @author Bartosz Biegalski
 * @date 28.03.2025
 */

#include <string>
#include <memory>
#include "typedef.hpp"

class XMLDocument
{
private:
    std::string documentName;
    int documentId;
    NamespaceMap namespaceMap;
    XMLRoot* xmlRoot;

public:
    explicit XMLDocument(XMLRoot* xmlRoot);

    std::string getDocumentName() const;
    int getDocumentId() const;
    NamespaceMap &getNamespaceMap();
    XMLRoot* getXmlRoot() const;

    void setDocumentName(const std::string &name);
    void setDocumentId(int id);
    void setNamespaceMap(const NamespaceMap &nsMap);
    void setXmlRoot(XMLRoot* xmlRoot);

    void printXMLDocument() const;
};

#endif // XMLDOCUMENT_HPP
