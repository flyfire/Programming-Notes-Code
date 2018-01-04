# Android类加载器

## 1 Android中的类加载器

Java中的ClassLoader机制我们应该已经非常熟悉了，而Android只是在此基础之上添加了扩展了新的ClassLoader，用于加载Dex中的Class。

Android中的类加载器继承关系为：

    ClassLoader
        |-->BaseDexClassLoader
             |-->PathClassLoader
             |-->DexClassLoader

PathClassLoader和DexClassLoader是Android平台的两个主要的ClassLoader，它们都继承自BaseDexClassLoader


### PathClassLoader

```java
public class PathClassLoader extends BaseDexClassLoader {
    public PathClassLoader(String dexPath, ClassLoader parent) {
        super(dexPath, null, null, parent);
    }
    public PathClassLoader(String dexPath, String libraryPath,
            ClassLoader parent) {
        super(dexPath, null, libraryPath, parent);
    }
}
```

PathClassLoader被用来加载本地文件系统上的文件或目录，但不能从网络上加载，**关键是它被用来加载系统类和我们的应用程序**。

- dexPath表示包含类和资源的jar或 apk文件列表，多个路径使用路径分隔符分开
- libraryPath表示so路径

### DexClassLoader

```java
public class DexClassLoader extends BaseDexClassLoader {
  
    public DexClassLoader(String dexPath, String optimizedDirectory,
            String libraryPath, ClassLoader parent) {
        super(dexPath, new File(optimizedDirectory), libraryPath, parent);
    }
}
```

DexClassLoader只是简单的继承了BaseDexClassLoader，DexClassLoader的类加载路径是在创建DexClassLoader对象时指定的，所以它可以加载任何目录下的Dex文件，
其主要用来加载`jar 、apk ，其实还包括 zip 文件或者直接加载dex文件`，它可以被用来执行未安装的代码或者未被应用加载过的代码。

参数说明：

- dexPath : 需要被加载的文件地址，可以多个，用路径分隔符分开
- optimizedDirectory : dex文件被加载后会被编译器优化，优化之后的dex存放路径(即将要用来存放ODEX的路径)，不可以为null。注释中也提到需要一个应用私有的可写的一个路径，以防止应用被注入攻击，并且给出了例子` File dexOutputDir = context.getDir("dex", 0) ;`
- libraryPath ：包含libraries的目录列表，plugin中有so文件，需要将so拷贝到本地目录，这里传入将要用来存放so目录的路径
- parent ：父类构造器

PathClassLoader的两个构造函数中调用父类构造器的时候第二个参数传null。即optimizedDirectory为null值，因为Apk在安装过程中
其内部的dex已经被提取并且执行过优化了，优化之后放在系统目录` /data/dalvik-cache `下。

### BaseDexClassLoader

PathClassLoader和DexClassLoader的并没有具体的实现，只是定义了不同的构造方法，功能全都继承自BaseDexClassLoader：


BaseDexClassLoader的实现也比较简单，其内部有一个关键字段`private final DexPathList pathList;`，DexPathList代码如下：

```java
public class BaseDexClassLoader extends ClassLoader {

    private final DexPathList pathList;

    public BaseDexClassLoader(String dexPath, File optimizedDirectory,
            String libraryPath, ClassLoader parent) {
        super(parent);
        this.pathList = new DexPathList(this, dexPath, libraryPath, optimizedDirectory);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        List<Throwable> suppressedExceptions = new ArrayList<Throwable>();
        Class c = pathList.findClass(name, suppressedExceptions);
        if (c == null) {
            ClassNotFoundException cnfe = new ClassNotFoundException("Didn't find class \"" + name + "\" on path: " + pathList);
            for (Throwable t : suppressedExceptions) {
                cnfe.addSuppressed(t);
            }
            throw cnfe;
        }
        return c;
    }

    @Override
    protected URL findResource(String name) {
        return pathList.findResource(name);
    }

    @Override
    protected Enumeration<URL> findResources(String name) {
        return pathList.findResources(name);
    }

    @Override
    public String findLibrary(String name) {
        return pathList.findLibrary(name);
    }


    @Override
    protected synchronized Package getPackage(String name) {
        if (name != null && !name.isEmpty()) {
            Package pack = super.getPackage(name);

            if (pack == null) {
                pack = definePackage(name, "Unknown", "0.0", "Unknown", "Unknown", "0.0", "Unknown", null);
            }
            return pack;
        }
        return null;
    }

    public String getLdLibraryPath() {
        StringBuilder result = new StringBuilder();
        for (File directory : pathList.getNativeLibraryDirectories()) {
            if (result.length() > 0) {
                result.append(':');
            }
            result.append(directory);
        }

        return result.toString();
    }

    @Override public String toString() {
        return getClass().getName() + "[" + pathList + "]";
    }
}
```

从源码可以看出，BaseDexClassLoader的方法调用都由DexPathList实现，DexPathList中就保存了ClassLoader对应的dex路径和so路径等。

关于DexPathList的分析可以查看[DexPathList类](DexPathList类.md)，熟悉DexPathList类后，可以总结出BaseDexClassLoader的构建流程

1. 在创建BaseDexClassLoader时，需要传入对应的参数，分别是
    - dexPath：需要被加载的文件路径，可以是`.apk .jar .dex .zip`文件
    - optimizedDirectory：用于存储odex的路径
    - libraryPath ：用于存放so的路径
    - parent：作为该类加载器的父类构造器
2. BaseDexClassLoader构造函数中会创建一个DexPathList对象，然后将加载类和资源的方法都交给DexPathList去实现
3. DexPathList的构建需要`ClassLoader、dexPath、libraryPath、optimizedDirectory`
4. DexPathList内部首先校验传入的参数，然后初始化`dexElements、nativeLibraryPathElements`等
5. 最终DexPathList的findClass方法通过遍历内部的dexElements，通过Element对象中存储的DexFile来加载Class

### ClassLoader的getSystemClassLoader

ClassLoader的getSystemClassLoader方法被改写了

```
private static ClassLoader createSystemClassLoader() {
        String classPath = System.getProperty("java.class.path", ".");
        return new PathClassLoader(classPath, BootClassLoader.getInstance());
}

public static ClassLoader getSystemClassLoader() {
    return SystemClassLoader.loader;
}

static private class SystemClassLoader {
       public static ClassLoader loader = ClassLoader.createSystemClassLoader();
}
```

可以看出现在返回的是`PathClassLoader`


## App中的ClassLoader

在Application类中执行下面代码

            Log.d(TAG, "ClassLoader.getSystemClassLoader():" + ClassLoader.getSystemClassLoader());

            Log.d(TAG, "getClassLoader():" + getClassLoader());

            Log.d(TAG, "MainActivity.class.getClassLoader():" + MainActivity.class.getClassLoader());

打印的结果为：

```
 ClassLoader.getSystemClassLoader(): 
    dalvik.system.PathClassLoader[DexPathList[[directory "."],nativeLibraryDirectories=[/vendor/lib64, /system/lib64]]]
AppContext: getClassLoader():                               
    dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/com.ztiany.classloader-1/base.apk"],nativeLibraryDirectories=[/data/app/com.ztiany.classloader-1/lib/arm64, /vendor/lib64, /system/lib64]]]
MainActivity.class.getClassLoader():    
    dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/com.ztiany.classloader-1/base.apk"],nativeLibraryDirectories=[/data/app/com.ztiany.classloader-1/lib/arm64, /vendor/lib64, /system/lib64]]]
```

可以看出默认的系统类加载器是只当了路径的PathClassLoader