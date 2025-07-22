#ifndef FILE_IMPORT_HPP
#define FILE_IMPORT_HPP

#include <string>
#include <GMLObject.hpp>
#include <XMLParser.hpp>
#include <NamespaceTool.hpp>
#include <XSDValidator.hpp>
#include <libxml++/libxml++.h>

/**
 * @file FileImport.hpp
 * @class FileImport
 * @brief Imports gml and perform parsing, validation etc.
 * @author Bartosz Biegalski
 */
class FileImport
{
private:
    std::unique_ptr<GMLObject> object;
    NamespaceTool namespaceTool;

public:
    void importGML(const std::string &fileName);
    GMLObject *getGMLObject();

    void parseXML();

    void retriveNamespaces();

    void validateNamespaces();

    void generateReport(int detailLevel);
    //  Report getReport();
};

#endif