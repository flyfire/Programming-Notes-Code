cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

# DIR
set(cpp_dir ${CMAKE_SOURCE_DIR}/src/main/cpp)

message("------------------------cmake: " ${cpp_dir})
message("------------------------cmake: " ${CMAKE_CXX_FLAGS})

# Fmod
add_library(lib_fmod SHARED IMPORTED )
set_target_properties(lib_fmod PROPERTIES IMPORTED_LOCATION  ${cpp_dir}/lib/${ANDROID_ABI}/libfmod.so)
add_library(lib_fmodL SHARED IMPORTED)
set_target_properties(lib_fmodL PROPERTIES IMPORTED_LOCATION  ${cpp_dir}/lib/${ANDROID_ABI}/libfmodL.so)
include_directories(${cpp_dir}/inc)

# Android log
find_library(log-lib  log)

# My Lib
add_library(native-lib SHARED src/main/cpp/common_platform.cpp src/main/cpp/play_sound.cpp src/main/cpp/common.cpp)

target_link_libraries(native-lib  ${log-lib} android lib_fmod lib_fmodL)