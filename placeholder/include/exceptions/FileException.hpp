/**
 * @file FileException.hpp
 * @class FileException
 * @brief Exception throw when file is in invalid format
 * @author Bartosz Biegalski
 * @date 26.03.2025
 */

#include "Exception.hpp"

class FileException : public Exception
{
public:
    explicit FileException(const std::string& msg) : Exception("File error: " + msg) {}
   
};