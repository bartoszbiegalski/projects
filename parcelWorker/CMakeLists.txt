cmake_minimum_required(VERSION 3.28)
project(parcelWorker)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(parcelWorker
        Fetcher.cpp
        KDTree.cpp
        main.cpp
        ParcelEdge.cpp
        ParcelPoint.cpp
        Point.cpp
        PointVector.cpp
        PowiatFetcher.cpp
        PowiatParser.cpp
        PowiatReader.cpp
        temp.cpp)
