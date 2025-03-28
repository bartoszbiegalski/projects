#include <tinyxml2.h>
#include <iostream>

int main() {
    // Tworzymy obiekt dokumentu XML
    tinyxml2::XMLDocument doc;
    
    // Ładujemy plik XML
    doc.LoadFile("assets/dane-BDOT500.gml");
    
    // Sprawdzamy, czy załadowanie powiodło się
    if (doc.ErrorID() != tinyxml2::XML_SUCCESS) {
        std::cerr << "Error loading XML file!" << std::endl;
        return -1;
    }

    // Pobieramy główny element XML (root)
    tinyxml2::XMLElement* root = doc.RootElement();
    
    if (root == nullptr) {
        std::cerr << "Error: No root element found!" << std::endl;
        return -1;
    }

    // Wypisujemy wszystkie przestrzenie nazw (xmlns) z elementu root
    for (const tinyxml2::XMLAttribute* attr = root->FirstAttribute(); attr != nullptr; attr = attr->Next()) {
        // Szukamy atrybutów, które zaczynają się od "xmlns"
            std::cout << "Namespace: " << attr->Name() << " = " << attr->Value() << std::endl;
    }

    return 0;
}
