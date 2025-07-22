#ifndef TYPEDEF_HPP
#define TYPEDEF_HPP

#include <map>
#include <list>
#include <string>
#include <iostream>
#include <regex>
#include <sstream>
#include <stdexcept>
#include <filesystem>
#include <vector>

#include <tinyxml2.h>
#include <libxml++/libxml++.h>
#include <yaml-cpp/yaml.h>
/**
 * @file Typedef.hpp
 * @brief Contains all constrained type definitions used in project
 * @author Bartosz Biegalski
 */

// jakie chcemy typy?
// - na pewno chcemy miec jakis pojemnik na bazy
// - na pewno nazwe na roota od xml-a (z jakiejś libki ktora jest w użyciu)
// - moze jakies flagi
// -
// -

using SchemaLocationList = std::list<std::string>;
using NamespaceMap = std::map<std::string, std::string>;
using SchemaLocationMap = std::map<std::string, std::string>;

#endif