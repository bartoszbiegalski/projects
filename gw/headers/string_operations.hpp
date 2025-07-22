#ifndef STRING_OPERATIONS_HPP
#define STRING_OPERATIONS_HPP

#include "Typedef.hpp"

std::string get_prefix(const std::string &attribute);

std::string get_suffix(const std::string &attribute);

bool is_prefix_valid(const std::string &attribute, const std::string &prefix);

bool is_suffix_valid(const std::string &attribute, const std::string &prefix);

bool validate_schemaLocation_stream(const std::string &schemaLocationStream);

SchemaLocationList get_schemaLocation_list(const std::string &schemaLocationStream);

#endif // STRING_OPERATIONS_HPP