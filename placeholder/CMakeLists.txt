cmake_minimum_required(VERSION 3.10)

project(MyProject VERSION 1.0 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

include_directories(${PROJECT_SOURCE_DIR}/include) # todo !!!! change

find_package(GDAL REQUIRED)
find_package(LibXml2 REQUIRED)

find_package(PkgConfig REQUIRED)
pkg_check_modules(LIBXMLXX REQUIRED libxml++-2.6)

add_subdirectory(src)

enable_testing()
add_subdirectory(tests)
