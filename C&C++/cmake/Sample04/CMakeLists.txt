cmake_minimum_required(VERSION 3.7)
project(Sample4)

set(CMAKE_C_STANDARD 99)

#包含当前目录，查找头文件
set(CMAKE_INCLUDE_CURRENT_DIR ON)

# 加入一个配置头文件，用于处理 CMake 对源码的设置
configure_file (
        "${PROJECT_SOURCE_DIR}/config.h.in"
        "${PROJECT_BINARY_DIR}/config.h"
)

# 是否使用自己的 MathFunctions 库
#option (USE_MYMATH "Use provided math implementation" ON)
option (USE_MYMATH "Use provided math implementation" OFF)

# 是否加入 MathFunctions 库
if (USE_MYMATH)
    include_directories ("${PROJECT_SOURCE_DIR}/Libs")
    add_subdirectory (Libs)
    set (EXTRA_LIBS ${EXTRA_LIBS} MathLib)
endif (USE_MYMATH)

#构建
#set(SOURCE_FILES main.c)
aux_source_directory(. SOURCE_FILES)
add_executable(Sample4 ${SOURCE_FILES} )
target_link_libraries (Sample4  ${EXTRA_LIBS})