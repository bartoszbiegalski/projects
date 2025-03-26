#include <iostream>
#include <libxml++/libxml++.h>
#include <libxml/xmlschemas.h> // Funkcje walidacji XSD

bool validate_with_xsd(const std::string& gml_file, const std::string& xsd_file) {
    xmlSchemaParserCtxtPtr schema_parser_ctxt = xmlSchemaNewParserCtxt(xsd_file.c_str());
    
    if (!schema_parser_ctxt) {
        std::cerr << "Nie można utworzyć kontekstu parsera XSD!" << std::endl;
        return false;
    }
    std::cout<<"wczytuje schemat XSD\n";
    // Wczytaj schemat XSD
    xmlSchemaPtr schema = xmlSchemaParse(schema_parser_ctxt);
    xmlSchemaFreeParserCtxt(schema_parser_ctxt);
    if (!schema) {
        std::cerr << "Nie można wczytać schematu XSD!" << std::endl;
        return false;
    }

    std::cout<<"tworze kontekst walidatora\n";
    // Stwórz kontekst walidatora
    xmlSchemaValidCtxtPtr valid_ctxt = xmlSchemaNewValidCtxt(schema);
    if (!valid_ctxt) {
        std::cerr << "Nie można utworzyć kontekstu walidacji!" << std::endl;
        xmlSchemaFree(schema);
        return false;
    }
    std::cout<<"wczytuje GML\n";
    // Wczytaj plik GML
    xmlDocPtr doc = xmlReadFile(gml_file.c_str(), nullptr, 0);
    if (!doc) {
        std::cerr << "Nie można wczytać pliku GML!" << std::endl;
        xmlSchemaFreeValidCtxt(valid_ctxt);
        xmlSchemaFree(schema);
        return false;
    }
    std::cout<<"zaczynam walidowac\n";
    // Walidacja dokumentu GML
    int result = xmlSchemaValidateDoc(valid_ctxt, doc);

    // Zwolnij zasoby
    xmlFreeDoc(doc);
    xmlSchemaFreeValidCtxt(valid_ctxt);
    xmlSchemaFree(schema);

    if (result == 0) {
        std::cout << "GML jest poprawny względem XSD!" << std::endl;
        return true;
    } else {
        std::cerr << "GML NIE jest poprawny względem XSD!" << std::endl;
        return false;
    }
}

int main(int argc, char* argv[]) {
    if (argc < 3) {
        std::cerr << "Użycie: " << argv[0] << " plik.gml schemat.xsd" << std::endl;
        return 1;
    }

    std::string gml_file = argv[1];
    std::string xsd_file = argv[2];

    bool is_valid = validate_with_xsd(gml_file, xsd_file);
    return is_valid ? 0 : 1;
}
