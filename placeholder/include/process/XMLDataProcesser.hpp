/**
 * @file XMLDataProcesser.hpp
 * @class XMLDataProcesser
 * @brief Gets request type and request data, proceses it.
 * @author Bartosz Biegalski
 * @date 27.03.2025
 */
#include <vector>
#include "file_handling/XMLDocument.hpp"

class XMLDataProcesser
{
private:
public:
    /// test method for dividing one xml with different bases to multi files 
    std::vector<std::unique_ptr<XMLDocument>>& divideDocument(const std::unique_ptr<XMLDocument>& sourceDocument, NamespaceMap &NamespaceMap);
};