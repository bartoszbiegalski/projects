add_library(MyLibrary STATIC
    process/XMLDataProcesser.cpp
    file_handling/FileImport.cpp
    file_handling/XMLDocument.cpp
)

target_include_directories(MyLibrary PUBLIC
    ${LIBXMLXX_INCLUDE_DIRS}
    ${TinyXML2_INCLUDE_DIRS}
    ${PROJECT_SOURCE_DIR}/include
)

# Linkowanie bibliotek
target_link_libraries(MyLibrary PUBLIC 
    ${LIBXMLXX_LIBRARIES}  # libxml++
    ${TinyXML2_LIBRARIES}   # TinyXML2
    GDAL::GDAL             # GDAL
    LibXml2::LibXml2       # LibXml2
)

# Dodaj główny plik wykonywalny
add_executable(MyProject main.cpp)

# Linkowanie do biblioteki statycznej MyLibrary
target_link_libraries(MyProject PRIVATE MyLibrary)