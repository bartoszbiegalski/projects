#ifndef NAMESPACETOOL_CPP
#define NAMESPACETOOL_CPP

#include "NamespaceTool.hpp"
#include "string_operations.hpp"
#include "YamlWorker.hpp"

NamespaceMap NamespaceTool::processSchemaLocations(const std::string schemaLocationStream)
{
    // some preprocessing. We should validate string if:
    // - if there is any " " in it
    // - if there are even number of whitespaces

    // after success, procede further

    SchemaLocationMap locationMap;
    SchemaLocationList locationList;

    std::string str = schemaLocationStream;

    locationList = get_schemaLocation_list(schemaLocationStream);

    for (auto it = locationList.begin(); it != locationList.end();)
    {
        auto next = it;
        std::advance(next, 1);

        if (next == locationList.end())
            break;

        locationMap.insert(std::make_pair(*it, *next));

        std::advance(it, 2);
    }

    return locationMap;
}

NamespaceMap NamespaceTool::connectMaps(const NamespaceMap &A, const NamespaceMap &B)
{
    NamespaceMap C;
    for (const auto &[keyA, valueA] : A)
    {
        auto it = B.find(valueA);
        if (it != B.end())
        {
            C[keyA] = it->second;
        }
    }
    return C;
}

NamespaceMap NamespaceTool::collectNamespaces(const std::string fileName)
{
    tinyxml2::XMLDocument doc;
    doc.LoadFile(fileName.c_str());
    tinyxml2::XMLElement *namespaceRoot = doc.RootElement();

    if (!namespaceRoot)
    {
        std::cout << "Root element is null\n";
        return {};
    }

    // TODO: class AppConfig for one, simple .init (see in ChatGPT)
    // now I will do it stupidly, hardcode "xmlns" and "xsi"

    // 1. Check for xmlns:gml, xmlns:xsi and xsi:schemaLocation

    bool isXmlnsGml, isXmlnsXsi, isXsiSchemaLocation = false;

    NamespaceMap namespaceMap;
    NamespaceMap schemaLocationsMap;
    for (const tinyxml2::XMLAttribute *attr = namespaceRoot->FirstAttribute(); attr; attr = attr->Next())
    {
        if (is_prefix_valid(attr->Name(), "xmlns"))
        {
            if (is_suffix_valid(attr->Name(), "gml"))
            {
                isXmlnsGml = true;
            }

            if (is_suffix_valid(attr->Name(), "xsi"))
            {
                isXmlnsXsi = true;
            }
            namespaceMap.insert(std::make_pair(get_suffix(attr->Name()), attr->Value()));
        }

        // const std::string &baseFile = "base.yaml";

        // namespaceMap::insert(std::make_pair(attr->Name(), attr->Value()));

        // we want to check for "xmlns" and "xsi"

        if (validate_schemaLocation_stream(attr->Value()) == false)
        {
            // std::cout << "schemaLocation should have event number of arguments\n";
        }
        else
        {
            schemaLocationsMap = this->processSchemaLocations(attr->Value());
        }
    }

    NamespaceMap connectedMap = this->connectMaps(namespaceMap, schemaLocationsMap);
    return connectedMap;
}

#endif // NAMESPACETOOL_CPP
