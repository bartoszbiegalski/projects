/**
 * @file typedef.hpp
 * @brief All type definitions used in project
 * @author Bartosz Biegalski
 * @date 27.03.2024
 */
#pragma once

#include <vector>
#include <map>
#include <libxml++/libxml++.h>
#include <tinyxml2.h> 

using NamespacePrefix = std::string;
using NamespaceURL = std::string;
using NamespaceMap = std::map<NamespacePrefix, NamespaceURL>;

using XMLRoot = xmlpp::Element;

