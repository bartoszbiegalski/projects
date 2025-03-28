/**
 * @file XMLDataProcesser.cpp
 * @class XMLDataProcesser
 * @brief Gets request type and request data, proceses it.
 * @author Bartosz Biegalski
 * @date 27.03.2025
 */
#include "process/XMLDataProcesser.hpp"
#include <iostream>

std::vector<std::unique_ptr<XMLDocument>>& XMLDataProcesser::divideDocument(const std::unique_ptr<XMLDocument>& sourceDocument, NamespaceMap &namespaceMap) {
    std::vector<std::unique_ptr<XMLDocument>> resultDocuments;

    for (auto &ns : namespaceMap) {
        std::cout<<ns.first<<" "<<ns.second<<std::endl;
    }

    return resultDocuments;
}