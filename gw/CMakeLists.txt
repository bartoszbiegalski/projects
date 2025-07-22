cmake_minimum_required(VERSION 3.16)

project(gw VERSION 0.1 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
#set(CMAKE_PREFIX_PATH "/usr/lib/x86_64-linux-gnu/cmake/Qt6")
#set(CMAKE_INCLUDE_PATH "/usr/include/x86_64-linux-gnu/qt6")

find_package(TinyXML2 REQUIRED)
find_package(PkgConfig REQUIRED)
find_package(yaml-cpp REQUIRED)

pkg_check_modules(LIBXMLXX REQUIRED libxml++-2.6)

set(PROJECT_SOURCES
    ### headers ###
    headers/Typedef.hpp

    headers/FileImport.hpp

    headers/GMLObject.hpp
    headers/XMLTree.hpp

    headers/XMLParser.hpp
    headers/NamespaceTool.hpp

    headers/string_operations.hpp
    headers/YamlWorker.hpp
    headers/XSDValidator.hpp
    headers/Reporter.hpp

    ### src ###
    src/main.cpp

    src/string_operations.cpp
    src/YamlWorker.cpp

    src/FileImport.cpp

    src/GMLObject.cpp
    src/XMLTree.cpp

    src/XMLParser.cpp
    src/NamespaceTool.cpp
    src/XSDValidator.cpp
    src/Reporter.cpp

)

add_executable(gw ${PROJECT_SOURCES})


target_include_directories(gw PRIVATE
    ${LibXml2_INCLUDE_DIRS}
    ${TinyXML2_INCLUDE_DIRS}
    ${LIBXMLXX_INCLUDE_DIRS}
    ${CMAKE_SOURCE_DIR}/headers
)

target_link_libraries(gw PRIVATE
    ${LibXml2_LIBRARIES}
    tinyxml2
    ${LIBXMLXX_LIBRARIES}
    yaml-cpp::yaml-cpp
)


set_target_properties(gw PROPERTIES
    ${BUNDLE_ID_OPTION}
    MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
    MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}
    MACOSX_BUNDLE TRUE
    WIN32_EXECUTABLE TRUE
)

include(GNUInstallDirs)
install(TARGETS gw
    BUNDLE DESTINATION .
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)

