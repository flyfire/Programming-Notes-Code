# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.8

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "E:\DevTools\CLion 2017.2.3\bin\cmake\bin\cmake.exe"

# The command to remove a file.
RM = "E:\DevTools\CLion 2017.2.3\bin\cmake\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/BinaryDiffPatch.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/BinaryDiffPatch.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/BinaryDiffPatch.dir/flags.make

CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj: CMakeFiles/BinaryDiffPatch.dir/flags.make
CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj: ../library.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj"
	D:\DevTools\MinGw-64\mingw64\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\BinaryDiffPatch.dir\library.cpp.obj -c E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\library.cpp

CMakeFiles/BinaryDiffPatch.dir/library.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/BinaryDiffPatch.dir/library.cpp.i"
	D:\DevTools\MinGw-64\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\library.cpp > CMakeFiles\BinaryDiffPatch.dir\library.cpp.i

CMakeFiles/BinaryDiffPatch.dir/library.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/BinaryDiffPatch.dir/library.cpp.s"
	D:\DevTools\MinGw-64\mingw64\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\library.cpp -o CMakeFiles\BinaryDiffPatch.dir\library.cpp.s

CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.requires:

.PHONY : CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.requires

CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.provides: CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.requires
	$(MAKE) -f CMakeFiles\BinaryDiffPatch.dir\build.make CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.provides.build
.PHONY : CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.provides

CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.provides.build: CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj


# Object files for target BinaryDiffPatch
BinaryDiffPatch_OBJECTS = \
"CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj"

# External object files for target BinaryDiffPatch
BinaryDiffPatch_EXTERNAL_OBJECTS =

libBinaryDiffPatch.dll: CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj
libBinaryDiffPatch.dll: CMakeFiles/BinaryDiffPatch.dir/build.make
libBinaryDiffPatch.dll: CMakeFiles/BinaryDiffPatch.dir/linklibs.rsp
libBinaryDiffPatch.dll: CMakeFiles/BinaryDiffPatch.dir/objects1.rsp
libBinaryDiffPatch.dll: CMakeFiles/BinaryDiffPatch.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX shared library libBinaryDiffPatch.dll"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\BinaryDiffPatch.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/BinaryDiffPatch.dir/build: libBinaryDiffPatch.dll

.PHONY : CMakeFiles/BinaryDiffPatch.dir/build

CMakeFiles/BinaryDiffPatch.dir/requires: CMakeFiles/BinaryDiffPatch.dir/library.cpp.obj.requires

.PHONY : CMakeFiles/BinaryDiffPatch.dir/requires

CMakeFiles/BinaryDiffPatch.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\BinaryDiffPatch.dir\cmake_clean.cmake
.PHONY : CMakeFiles/BinaryDiffPatch.dir/clean

CMakeFiles/BinaryDiffPatch.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\cmake-build-debug E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\cmake-build-debug E:\code\studio\my_github\Repository\Android\JNI\BinaryDiffPatchWindows\cmake-build-debug\CMakeFiles\BinaryDiffPatch.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/BinaryDiffPatch.dir/depend
