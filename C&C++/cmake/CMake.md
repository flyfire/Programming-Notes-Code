
# cmake开发方式

AndroidStudio2.2用于构建原生库的默认工具是 CMake。如果创建新的原生库，则应使用 CMake。

需要下载的SDK组件：

- Android 原生开发工具包 (NDK)：这套工具集允许您为 Android 使用 C 和 C++ 代码，并提供众多平台库，让您可以管理原生 Activity 和访问物理设备组件，例如传感器和触摸输入。
- CMake：一款外部构建工具，可与 Gradle 搭配使用来构建原生库。
- LLDB：一种调试程序，Android Studio 使用它来调试原生代码。


## CMake命令

### CMake中预定义的可用的变量

- CMAKE_SOURCE_DIR：这是包含顶级CMakeLists.txt的目录，即顶级源目录


### 操作

- ${xxx-variable}：用于引用已定义的变量
- message()：用于输入日志，比如`message("CMAKE_SOURCE_DIR : " ${CMAKE_SOURCE_DIR})`
- project(hello)：给工程起个名字
- include_directories()：添加头文件路径
- aux_source_directory()：添加源目录
- add_executable()：添加构建目标

### 参数

- CMAKE_CXX_FLAGS：是CMake传给C++编译器的编译选项，比如`set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")`


## 引用

- [使用CMake组织C++工程](https://elloop.github.io/tools/2016-04-04/learning-cmake-0)
- [CMake文档](https://cmake.org/cmake/help/v3.0/index.html#)
- [CMake Wiki](https://cmake.org/Wiki/CMake)
- [CMake_Useful_Variables](https://cmake.org/Wiki/CMake_Useful_Variables)