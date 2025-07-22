#ifndef STRING_OPERATIONS_CPP
#define STRING_OPERATIONS_CPP

#include "string_operations.hpp"

std::string get_prefix(const std::string &attribute)
{
    auto pos = attribute.find(':');
    return attribute.substr(0, pos);
}

std::string get_suffix(const std::string &attribute)
{
    auto pos = attribute.find(':');
    return attribute.substr(pos + 1);
}

bool is_prefix_valid(const std::string &attribute, const std::string &prefix)
{
    // we want to check if in attribute (i.e. "abc:def"), "abc" =? prefix
    auto pos = attribute.find(':');
    if (pos == std::string::npos)
    {
        return false;
    }

    std::string suffix = attribute.substr(0, pos);
    return suffix == prefix;
}

bool is_suffix_valid(const std::string &attribute, const std::string &prefix)
{
    // we want to check if in attribute (i.e. "abc:def"), "def" =? prefix
    auto pos = attribute.find(':');
    if (pos == std::string::npos)
    {
        return false;
    }

    std::string suffix = attribute.substr(pos + 1);
    return suffix == prefix;
}

bool validate_schemaLocation_stream(const std::string &schemaLocationStream)
{
    // - if there are odd number of whitespaces - return false

    // return true

    std::string str = schemaLocationStream;

    std::istringstream iss(str);
    std::string attributeValue;
    SchemaLocationList l;

    while (iss >> attributeValue)
    {
        l.push_back(attributeValue);
    }

    if (l.size() % 2 != 0)
    {
        return false;
    }

    return true;
}

// to change: one function that returns schemaLocationList or error

SchemaLocationList get_schemaLocation_list(const std::string &schemaLocationStream)
{
    std::string str = schemaLocationStream;

    std::istringstream iss(str);
    std::string attributeValue;
    SchemaLocationList l;

    while (iss >> attributeValue)
    {
        l.push_back(attributeValue);
    }
    return l;
}

#endif // STRING_OPERATIONS_CPP