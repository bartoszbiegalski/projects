#include <iostream>
#include <memory>
#include <tinyxml2.h>

#include "file_handling/FileImport.hpp"
#include "exceptions/Exception.hpp"

NamespaceMap FileImport::createNamespaceMap(const std::string &documentName)
{
    tinyxml2::XMLDocument doc;
    doc.LoadFile(documentName.c_str());

    if (doc.ErrorID() != tinyxml2::XML_SUCCESS)
    {
        std::cerr << "Error loading XML file!" << std::endl;
        return {};
    }

    tinyxml2::XMLElement *root = doc.RootElement();
    if (!root)
    {
        std::cerr << "Error: No root element found!" << std::endl;
        return {};
    }

    NamespaceMap namespaceMap;

    for (const tinyxml2::XMLAttribute *attr = root->FirstAttribute(); attr; attr = attr->Next())
    {
        namespaceMap.insert(std::make_pair(attr->Name(), attr->Value()));
    }
    doc.Clear();

    return namespaceMap;
}

std::unique_ptr<XMLDocument> FileImport::createXMLDocument(std::string documentName)
{
    xmlpp::DomParser parser;
    
    parser.parse_file(documentName);

    if (!parser) {
        throw Exception("Błąd podczas parsowania pliku XML: " + documentName);
    }

    xmlpp::Element* root = parser.get_document()->get_root_node();

    if (!root) {
        throw Exception("Nie znaleziono korzenia w pliku XML: " + documentName);
    }

    auto xmlDocument = std::make_unique<XMLDocument>(root);
    xmlDocument->setDocumentName(documentName);

    xmlDocument->setNamespaceMap(createNamespaceMap(documentName));

    return xmlDocument;
}



void FileImport::importFiles(std::vector<std::string> &fileNames)
{
    for (auto &file : fileNames)
    {
       this->documents.push_back(createXMLDocument(file));
    }
}

const std::vector<std::unique_ptr<XMLDocument>> &FileImport::getDocuments() const
{
    return this->documents;
}
