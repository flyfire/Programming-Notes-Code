cmake_minimum_required(VERSION 3.8)
project(Play-with-Algorithms)

set(CMAKE_CXX_STANDARD 11)

aux_source_directory(sort-basic SOURCE_FILES)
add_executable(Play-with-Algorithms ${SOURCE_FILES})