/**
 * @file XMLDataRetriver.hpp
 * @class XMLDataRetriver
 * @brief Retrive informations from object, pushes it next to process.
 * @author Bartosz Biegalski
 * @date 17.03.2025
 */

#include <memory>
#include <vector>
#include <libxml++/libxml++.h>
#include "file_handling/XMLDocument.hpp"

class XMLDataRetriver
{
private:
    std::vector<XMLDocument> xmlDocumentsList;

public:
    XMLDataRetriver(std::vector<XMLDocument> documentsList);

    std::vector<XMLDocument> getXMLDocumentsList();

    void divideDocument();
};