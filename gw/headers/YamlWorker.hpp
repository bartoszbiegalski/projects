#ifndef YAMLWORKER_HPP
#define YAMLWORKER_HPP

/**
 * @file YamlWorker.hpp
 * @class YamlWorker
 * @brief Singleton-class for yaml operations
 * @author Bartosz Biegalski
 */

#include "Typedef.hpp"

class YamlWorker
{
private:
    YamlWorker() = delete;

public:
    static void check_required_attributes(const std::string &fileName);
};

#endif // YAMLWORKER_HPP