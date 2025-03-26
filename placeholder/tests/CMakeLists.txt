find_package(GTest REQUIRED)

add_executable(test_myproject test_main.cpp)

target_link_libraries(test_myproject PRIVATE 
    MyLibrary 
    GTest::GTest 
    GTest::Main
    GDAL::GDAL 
    LibXml2::LibXml2 
    ${LIBXMLXX_LIBRARIES}
)
