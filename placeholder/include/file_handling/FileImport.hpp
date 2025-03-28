/**
 * @file FileImport.hpp
 * @class FileImport
 * @brief Imports xml and returns it in given format
 * @author Bartosz Biegalski
 * @date 18.03.2025
 */

#include <vector>
#include <libxml++/libxml++.h>
#include "file_handling/XMLDocument.hpp"

class FileImport
{
private:
    NamespaceMap createNamespaceMap(const std::string &documentName);

    std::vector<std::unique_ptr<XMLDocument>> documents;

    std::unique_ptr<XMLDocument> createXMLDocument(std::string documentName);


public:
    void importFiles(std::vector <std::string> &fileNames);

    const std::vector<std::unique_ptr<XMLDocument>>& getDocuments() const;
   
};