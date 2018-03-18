cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

# DIR
set(cpp_dir ${CMAKE_SOURCE_DIR}/src/main/cpp)

message("------------------------cmake: " ${cpp_dir})
message("------------------------cmake: " ${CMAKE_CXX_FLAGS})

# Android log
find_library(log-lib  log)

add_library(native-lib SHARED
${cpp_dir}/native-lib.c
${cpp_dir}/bionic-api.c
)

target_link_libraries(native-lib  ${log-lib})