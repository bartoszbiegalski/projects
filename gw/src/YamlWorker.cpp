#ifndef YAMLWORKER_CPP
#define YAMLWORKER_CPP

/**
 * @file YamlWorker.cpp
 * @class YamlWorker
 * @brief Singleton-class for yaml operations
 * @author Bartosz Biegalski
 */

#include "YamlWorker.hpp"

void YamlWorker::check_required_attributes(const std::string &fileName)
{
    YAML::Node base = YAML::LoadFile(fileName);
    if (base["required_attributes"])
    {
        std::cout << base["required_attributes"].as<std::string>() << "\n";
    }
}

#endif // YAMLWORKER_CPP