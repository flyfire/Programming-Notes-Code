# AndroidStudio NDK 开发

Android Studio 2.2 稳定版增强了 C++ 的开发能力，能够使用 ndk-build 或 CMake 去编译和调试项目里的 C++ 代码，
ndk-build 或 CMake 比用 Gradle 去编译 C++ 更好

## 注意

- 为了能够在 Android Studio 里使用 CMake 或 ndk-build，你必须使用 Android Studio 2.2 或更高版本 和 Android Plugin for Gradle version 2.2.0 或更高版本
- 由于 Android Studio 编译 C 和 C++ 代码默认使用 CMake, 因此我建议使用 Experimental Android Plugin for Gradle 的用户也切换到 Android Plugin for Gradle version 2.2.0 或更高版本，这样既可以使用 Android Plugin for Gradle 的稳定版本，也可以使用 CMake 或 ndk-build 来提高编译速度

## Gradle配置

配置好Android.md和Application.mk文件，然后编写好相关的c/c++代码

```
android {
    compileSdkVersion 26
    buildToolsVersion "26.0.1"

    defaultConfig {
        applicationId "com.ztiany.sample2"
        minSdkVersion 15
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"

        externalNativeBuild {
            ndkBuild {
                arguments "NDK_APPLICATION_MK:=src/main/jni/Application.mk"
                cFlags "-DTEST_C_FLAG1", "-DTEST_C_FLAG2"
                cppFlags "-DTEST_CPP_FLAG2", "-DTEST_CPP_FLAG2"
                abiFilters "armeabi-v7a", "armeabi", "x86"
            }
        }
    }
    
    externalNativeBuild {
        ndkBuild {
            path "src/main/jni/Android.mk"
        }
    }
```

- path 用来指定 Android.mk 的路径
- arguments 用来指定 Application.mk 的路径
- abiFilters 用来指定生成哪些平台的 .so 文件
- cFlags 和 cppFlags 是用来设置环境变量的, 一般不需要动，和示例一样就好，

关于externalNativeBuild的详细配置可以参考

- https://developer.android.com/studio/projects/add-native-code.html

如上配置，Android Studio 会检查 app/src/main/jni 目录下是否有 C 或 C++ 代码, 如果有, 就根据 build.gradle 里的配置调用 CMake 或 ndk-build 去编译它
运行项目即可自动生成并打包.so文件了。