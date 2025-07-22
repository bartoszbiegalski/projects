#ifndef REPORTER_HPP
#define REPORTER_HPP

/**
 * @file Reporter.hpp
 * @class Reporter
 * @brief Declarations of Reporter class. Given level of details, returns raport
 * @author Bartosz Biegalski
 */

#include <libxml++/libxml++.h>
#include "GMLObject.hpp"

class Reporter
{
private:
    Reporter() = delete;

    std::string retriveAttributeMap(xmlpp::Element *element);

public:
    static void printRaport(GMLObject *gmlObject, int detailLevel);
};

#endif // REPORTER_HPP