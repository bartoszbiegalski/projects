/**
 * @file Exception.hpp
 * @class Exception
 * @brief Core Exception class
 * @author Bartosz Biegalski
 * @date 26.03.2025
 */

#include <exception>
#include <string>
class Exception : public std::exception {
protected:
    std::string message;
public:
    explicit Exception(const std::string& msg) : message(msg) {}
    virtual const char* what() const noexcept override {
        return message.c_str();
    }
};