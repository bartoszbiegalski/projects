/**
 * @file XSDValidator.hpp
 * @class XSDValidator
 * @brief Declarations of XSDValidator class. Takes map of namespaces, searches
 * for .xsd files and parses them
 * @author Bartosz Biegalski
 */

#include "XSDValidator.hpp"
#include <stdexcept>

void XSDValidator::validateNamespaces(GMLObject *gmlObject)
{
    static const std::regex url_regex(R"(https?://[^\s]+)");
    const std::string schemaBasePath = "../resources/";

    // 1. Załaduj wszystkie walidatory z mapy namespace → xsd
    for (const auto &ns : gmlObject->getNamespaceMap())
    {
        const std::string &prefix = ns.first;
        std::string xsdPath = ns.second;
        if (!std::regex_match(xsdPath, url_regex))
        {
            std::cout << ns.first << " " << ns.second << std::endl;

            xsdPath = schemaBasePath + xsdPath;
            if (!std::filesystem::exists(xsdPath))
            {
                throw std::runtime_error("XSD file does not exist: " + xsdPath);
            }
            std::cout << "lala1\n";
            xmlSchemaParserCtxtPtr parserCtxt = xmlSchemaNewParserCtxt(xsdPath.c_str());
            std::cout << "lala2\n";

            if (!parserCtxt)
                throw std::runtime_error("Failed to create parser context for: " + xsdPath);
            std::cout << "lala3\n";

            xmlSchemaPtr schema = xmlSchemaParse(parserCtxt);
            std::cout << "lala4\n";

            xmlSchemaFreeParserCtxt(parserCtxt);
            std::cout << "lala5\n";

            if (!schema)
                throw std::runtime_error("Failed to parse schema: " + xsdPath);

            xmlSchemaValidCtxtPtr validCtxt = xmlSchemaNewValidCtxt(schema);
            if (!validCtxt)
            {
                xmlSchemaFree(schema);
                throw std::runtime_error("Failed to create validation context for: " + xsdPath);
            }
            std::cout << "lala3\n";

            validatorMap[prefix] = {schema, validCtxt};
            std::cout << "created context for namespace: " << prefix << std::endl;
        }
    }

    // 2. Pobierz root dokumentu XML
    xmlNodePtr root = xmlDocGetRootElement(gmlObject->getRawDoc());

    // traverseAndValidate(root);

    // 4. Zwolnij schematy po walidacji
    // for (auto &[prefix, ctx] : validatorMap)
    // {
    //     xmlSchemaFreeValidCtxt(ctx.validCtxt);
    //     xmlSchemaFree(ctx.schema);
    // }
    // validatorMap.clear();
}

void XSDValidator::traverseAndValidate(xmlNodePtr node)
{
    for (; node; node = node->next)
    {
        std::cout << "lala\n";
        if (node->type == XML_ELEMENT_NODE && node->ns && node->ns->prefix)
        {
            std::string prefix = reinterpret_cast<const char *>(node->ns->prefix);

            auto it = validatorMap.find(prefix);
            if (it != validatorMap.end())
            {
                validateSubtree(node, it->second.validCtxt);
            }
        }

        // traverseAndValidate(node->children); // rekurencja
    }
}

void XSDValidator::validateSubtree(xmlNodePtr node, xmlSchemaValidCtxtPtr validCtxt)
{
    xmlDocPtr tmpDoc = xmlNewDoc(BAD_CAST "1.0");
    xmlNodePtr copy = xmlDocCopyNode(node, tmpDoc, 1);
    xmlDocSetRootElement(tmpDoc, copy);

    int result = xmlSchemaValidateDoc(validCtxt, tmpDoc);
    if (result == 0)
    {
        std::cout << "[OK] Element <" << node->name << "> valid\n";
    }
    else if (result > 0)
    {
        std::cerr << "[ERROR] Element <" << node->name << "> is INVALID\n";
    }
    else
    {
        std::cerr << "[ERROR] Validation failed on <" << node->name << ">\n";
    }

    xmlFreeDoc(tmpDoc);
}
