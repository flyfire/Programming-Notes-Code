# CMake简要学习

## 什么是 CMake

>All problems in computer science can be solved by another level of indirection.


你或许听过好几种 Make 工具，例如：
 - GNU Make
 - QT 的 qmake
 - 微软的 MS nmakeBSD Make（pmake）
 - Makepp，等等。

 这些 Make 工具遵循着不同的规范和标准，所执行的 Makefile 格式也千差万别。这样就带来了一个严峻的问题：如果软件想跨平台，必须要保证能够在不同平台编译。而如果使用上面的 Make 工具，就得为每一种标准写一次 Makefile ，这将是一件让人抓狂的工作。

CMake就是针对上面问题所设计的工具：它首先允许开发者编写一种平台无关的 CMakeList.txt 文件来定制整个编译流程，然后再根据目标用户的平台进一步生成所需的本地化 Makefile 和工程文件，如 Unix 的 Makefile 或 Windows 的 Visual Studio 工程。从而做到“Write once, run everywhere”。显然，CMake 是一个比上述几种 make 更高级的编译配置工具。一些使用 CMake 作为项目架构系统的知名开源项目有 VTK、ITK、KDE、OpenCV、OSG 等

## 示例

- Sample01：最简单的Cmake示例
- Sample02：编译多个文件
- Sample03：编译不同目录下的多个文件
- Sample04：自定义编译选项


## 引用

- [make makefile cmake qmake都是什么，有什么区别？](https://www.zhihu.com/question/27455963)
- [CMake官网教程](https://cmake.org/documentation/)
- [CMake Wiki](https://cmake.org/Wiki/CMake)
- [CMake 入门实战](http://hahack.com/codes/cmake/)
- [CMake简要教程](http://www.jianshu.com/p/bbf68f9ddffa)
