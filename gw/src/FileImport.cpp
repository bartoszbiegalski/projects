/**
 * @file FileImport.cpp
 * @class FileImport
 * @brief Imports gml and perform parsing, validation etc.
 * @author Bartosz Biegalski
 */

#include "FileImport.hpp"
#include "GMLObject.hpp"
#include "XMLTree.hpp"
#include "Reporter.hpp"

void FileImport::importGML(const std::string &fileName)
{
    // we should do here some operations with path finding,
    // file format etc. before creating GMLObject

    this->object = std::make_unique<GMLObject>(nullptr);
    this->object->setName(fileName);
}

GMLObject *FileImport::getGMLObject()
{
    return this->object.get();
}

void FileImport::parseXML()
{
    XMLParser::parseGMLObject(this->object.get());
    XMLParser::setXMLRoot(this->object.get());
}

void FileImport::retriveNamespaces()
{
    this->object->setNamespaceMap(this->namespaceTool.collectNamespaces(this->object->getName()));

    this->object->setPrefixMap(this->object->calculatePrefixMap());
}

void FileImport::validateNamespaces()
{
    if (this->object->getNamespaceMap().size() != 0)
    {
        XSDValidator v;
        v.validateNamespaces(this->object.get());
    }
}

void FileImport::generateReport(int detailLevel)
{
    Reporter::printRaport(this->object.get(), detailLevel);
}
