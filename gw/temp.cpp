include <stdio.h>
#include <libxml/parser.h>
#include <libxml/xmlschemas.h>

int main(int argc, char **argv)
{
    if (argc != 3)
    {
        fprintf(stderr, "Użycie: %s <plik.xml> <schemat.xsd>\n", argv[0]);
        return 1;
    }

    const char *xmlFile = argv[1];
    const char *xsdFile = argv[2];

    // Inicjalizacja parsera
    xmlInitParser();
    LIBXML_TEST_VERSION

    // Wczytanie schematu XSD
    xmlSchemaParserCtxtPtr parserCtxt = xmlSchemaNewParserCtxt(xsdFile);
    if (parserCtxt == NULL)
    {
        fprintf(stderr, "Nie udało się utworzyć kontekstu parsera schematu\n");
        return 1;
    }

    xmlSchemaPtr schema = xmlSchemaParse(parserCtxt);
    if (schema == NULL)
    {
        fprintf(stderr, "Nie udało się sparsować schematu XSD\n");
        xmlSchemaFreeParserCtxt(parserCtxt);
        return 1;
    }
    xmlSchemaFreeParserCtxt(parserCtxt);

    // Utworzenie kontekstu walidacji
    xmlSchemaValidCtxtPtr validCtxt = xmlSchemaNewValidCtxt(schema);
    if (validCtxt == NULL)
    {
        fprintf(stderr, "Nie udało się utworzyć kontekstu walidacji\n");
        xmlSchemaFree(schema);
        return 1;
    }

    // Parsowanie dokumentu XML
    xmlDocPtr doc = xmlReadFile(xmlFile, NULL, 0);
    if (doc == NULL)
    {
        fprintf(stderr, "Nie udało się wczytać dokumentu XML\n");
        xmlSchemaFreeValidCtxt(validCtxt);
        xmlSchemaFree(schema);
        return 1;
    }

    // Walidacja dokumentu
    int ret = xmlSchemaValidateDoc(validCtxt, doc);
    if (ret == 0)
    {
        printf("Dokument jest poprawny względem schematu XSD\n");
    }
    else if (ret > 0)
    {
        printf("Dokument jest NIEpoprawny względem schematu XSD\n");
    }
    else
    {
        printf("Błąd walidacji\n");
    }

    // Zwolnienie zasobów
    xmlFreeDoc(doc);
    xmlSchemaFreeValidCtxt(validCtxt);
    xmlSchemaFree(schema);
    xmlCleanupParser();

    return (ret == 0) ? 0 : 1;
}
