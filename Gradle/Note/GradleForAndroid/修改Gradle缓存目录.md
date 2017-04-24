默认情况下，gradle从仓库下载的依赖都会存储在.gradle中，而windows的用户的.gradle文件在`userhome/.gradle中`，随着日积月累，这个文件夹会变得越来越大，一般我们不喜欢C判断被占用太多的空间，所以需要修改默认的.gradle文件夹位置。

只需要在系统环境变量里设置` GRADLE_USER_HOME`的目录即可。

![](image/gradle_cache_system.png)

另外如果是使用的idea，需要在Setting中配置：

![](image/gradle_cache_idea.png)
