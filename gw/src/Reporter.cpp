/**
 * @file Reporter.cpp
 * @class Reporter
 * @brief Definitions of Reporter class.
 * @author Bartosz Biegalski
 */

#include "Reporter.hpp"
#include <stdexcept>

std::string Reporter::retriveAttributeMap(xmlpp::Element *element)
{
}

void Reporter::printRaport(GMLObject *gmlObject, int detailLevel)
{
    /**
     * level [1] - just names of first children
     *  [..]
     *
     *
     */
    std::cout << "File name: " << gmlObject->getName() << std::endl;

    if (gmlObject->getNamespaceMap().size() > 0)
    {
        std::cout << "Avaible namespaces: \n";
        for (auto i : gmlObject->getPrefixMap())
        {
            std::cout << " - " << i.first << " #" << i.second.size() << std::endl;
        }
        std::cout << "\n";

        if (detailLevel > 0)
        {
            std::cout << "Specific info: " << "\n";

            for (auto i : gmlObject->getPrefixMap())
            {
                std::map<std::string, int> printMap;

                for (const auto &j : i.second)
                {
                    printMap[j->get_name()]++;
                }

                if (i.second.size() > 0)
                {
                    std::cout << i.first << ": \n";

                    for (const auto &j : printMap)
                    {
                        for (int x = 0; x < detailLevel; x++)
                            std::cout << "\t";
                        std::cout << i.first << ":" << j.first << " #" << j.second << std::endl;
                    }
                    std::cout << "\n";
                }
            }
        }
    }
}
