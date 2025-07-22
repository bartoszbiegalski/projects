#ifndef NAMESPACETOOL_HPP
#define NAMESPACETOOL_HPP

/**
 * @file NamespaceTool.hpp
 * @class NamespaceTool
 * @brief Class responsible for extracting xmlns schemes
 * from gml file
 * @author Bartosz Biegalski
 */

#include "Typedef.hpp"

// we want to retrive in simple steps xmlns. To do so, we decide that they
// have to be in parent's parameters. If not, we will return error respectively.

// first simple assumption is that all xmlns are in topmost parent in xml tree.
class NamespaceTool
{
private:
    /**
     * @brief gets one string with stream of schemeNames and location of xml schemes (i.e. .xsd) and map of prefixes and schemeNames.
     * Connects locations with keys.
     */
    NamespaceMap processSchemaLocations(const std::string schemaLocationStream);

    /**
     * @brief connects two NamespaceMap's, values from map A with keys from map B
     *
     */
    NamespaceMap connectMaps(const NamespaceMap &A, const NamespaceMap &B);

public:
    /**
     * @brief collect Namespaces from given gml
     */
    NamespaceMap collectNamespaces(const std::string fileName);
};

#endif // NAMESPACETOOL_HPP