/**
 * @file XMLDataRetriver.cpp
 * @class XMLDataRetriver
 * @brief Retrive informations from object, pushes it next to process.
 * @author Bartosz Biegalski
 * @date 17.03.2025
 */

#include <iostream>
#include <libxml++/libxml++.h>
#include <vector>
#include "process/XMLDataRetriver.hpp"

XMLDataRetriver::XMLDataRetriver(std::vector<XMLDocument> xmlDocumentsList) {
}

std::vector<XMLDocument> XMLDataRetriver::getXMLDocumentsList()
{
    //return this->xmlDocumentsList;
}

void XMLDataRetriver::divideDocument()
{   /*
    // Zakładając, że jest tylko jeden plik w documentsList
    auto document = documentsList[0]->get_document();

    // Pobranie korzenia dokumentu (FeatureCollection)
    auto node = document->get_root_node();
    auto element = dynamic_cast<xmlpp::Element*>(node);

    if (!element)
    {
        std::cerr << "Błąd: Główny element XML nie jest poprawnym elementem!" << std::endl;
        return;
    }

    std::cout << "Element główny: " << element->get_name() << " " << element->get_namespace_uri() << " " << element->get_namespace_prefix()<<std::endl;
    */
}   
