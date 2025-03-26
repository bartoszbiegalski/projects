add_library(MyLibrary STATIC
    logic/RequestData.cpp
    loader/Import.cpp
)

target_include_directories(MyLibrary PUBLIC ${LIBXMLXX_INCLUDE_DIRS})
target_link_libraries(MyLibrary PUBLIC ${LIBXMLXX_LIBRARIES})

target_link_libraries(MyLibrary PUBLIC GDAL::GDAL)
target_link_libraries(MyLibrary PUBLIC LibXml2::LibXml2)

target_include_directories(MyLibrary PUBLIC ${PROJECT_SOURCE_DIR}/include)

add_executable(MyProject main.cpp)
target_link_libraries(MyProject PRIVATE MyLibrary)
