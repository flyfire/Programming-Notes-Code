```java
/**
 *与ClassLoader关联的一对条目列表。其中一个列表是dex /资源路径
 *  
 *  类路径条目可以是以下任何一个：
 *      1. 可选的jar或zip文件
 *      2. 顶级{classes.dex}文件以及任意资源
 *      3. 一个普通的dex文件（不能关联资源）
 */
final class DexPathList {

    private static final String DEX_SUFFIX = ".dex";
    private static final String zipSeparator = "!/";

    /** class definition context */
    private final ClassLoader definingContext;

    // dex /资源（类路径）元素的列表。
    private final Element[] dexElements;

    /** List of native library path elements. */
    private final Element[] nativeLibraryPathElements;

    /** List of application native library directories. */
    private final List<File> nativeLibraryDirectories;

    /** List of system native library directories. */
    private final List<File> systemNativeLibraryDirectories;

   
    private final IOException[] dexElementsSuppressedExceptions;


    /**
     * 构造方法：
     *
     * optimizedDirectory参数说明中说到如果optimizedDirectory为null则使用系统默认路径代替，这个默认路径也就是/data/dalvik-cache/目录，
     * 这个一般情况下是没有权限去访问的，所以这也就解释了为什么我们只能用DexClassLoader去加载类而不用PathClassLoader。
     */
    public DexPathList(ClassLoader definingContext, String dexPath,
            String libraryPath, File optimizedDirectory) {

        //用来验证参数的合法性的
        if (definingContext == null) {
            throw new NullPointerException("definingContext == null");
        }

        if (dexPath == null) {
            throw new NullPointerException("dexPath == null");
        }

        if (optimizedDirectory != null) {
            if (!optimizedDirectory.exists())  {
                throw new IllegalArgumentException("optimizedDirectory doesn't exist: "+ optimizedDirectory);
            }

            if (!(optimizedDirectory.canRead() && optimizedDirectory.canWrite())) {
                throw new IllegalArgumentException( "optimizedDirectory not readable/writable: "   + optimizedDirectory);
            }
        }


        this.definingContext = definingContext;

        /*接下里初始化各个成员*/
        ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
        // 
        // 解析或保存dex路径
        this.dexElements = makePathElements(splitDexPath(dexPath), optimizedDirectory, suppressedExceptions);

        //下面是解析和初始化native lib的路径
        // Native libraries may exist in both the system and
        // application library paths, and we use this search order:
        //
        //   1. This class loader's library path for application libraries (libraryPath):
        //   1.1. Native library directories
        //   1.2. Path to libraries in apk-files
        //   2. The VM's library path from the system property for system libraries
        //      also known as java.library.path
        //
        // This order was reversed prior to Gingerbread; see http://b/2933456.
        this.nativeLibraryDirectories = splitPaths(libraryPath, false/*directoriesOnly*/);
        this.systemNativeLibraryDirectories =  splitPaths(System.getProperty("java.library.path"), true/*directoriesOnly*/);
        List<File> allNativeLibraryDirectories = new ArrayList<>(nativeLibraryDirectories);
        allNativeLibraryDirectories.addAll(systemNativeLibraryDirectories);

        this.nativeLibraryPathElements = makePathElements(allNativeLibraryDirectories, null, suppressedExceptions);

        if (suppressedExceptions.size() > 0) {
            this.dexElementsSuppressedExceptions = suppressedExceptions.toArray(new IOException[suppressedExceptions.size()]);
        } else {
            dexElementsSuppressedExceptions = null;
        }
    }



    @Override public String toString() {
        List<File> allNativeLibraryDirectories = new ArrayList<>(nativeLibraryDirectories);
        allNativeLibraryDirectories.addAll(systemNativeLibraryDirectories);

        File[] nativeLibraryDirectoriesArray =
                allNativeLibraryDirectories.toArray(
                    new File[allNativeLibraryDirectories.size()]);

        return "DexPathList[" + Arrays.toString(dexElements) +
            ",nativeLibraryDirectories=" + Arrays.toString(nativeLibraryDirectoriesArray) + "]";
    }

    /**
     * For BaseDexClassLoader.getLdLibraryPath.
     */
    public List<File> getNativeLibraryDirectories() {
        return nativeLibraryDirectories;
    }

    /**
     * Splits the given dex path string into elements using the path
     * separator, pruning out any elements that do not refer to existing
     * and readable files. (That is, directories are not included in the
     * result.)
     */
    private static List<File> splitDexPath(String path) {
        return splitPaths(path, false);
    }

    /**
     * Splits the given path strings into file elements using the path
     * separator, combining the results and filtering out elements
     * that don't exist, aren't readable, or aren't either a regular
     * file or a directory (as specified). Either string may be empty
     * or {@code null}, in which case it is ignored. If both strings
     * are empty or {@code null}, or all elements get pruned out, then
     * this returns a zero-element list.
     *
     *使用separator将给定的路径字符串拆分为文件元素，合并结果并过滤掉不存在的元素，不可读或不是常规文件或目录。
     *directoriesOnly为ture时才会进行校验
     */



    private static List<File> splitPaths(String searchPath, boolean directoriesOnly) {
        List<File> result = new ArrayList<>();

        if (searchPath != null) {
            for (String path : searchPath.split(File.pathSeparator)) {
                if (directoriesOnly) {
                    try {
                        StructStat sb = Libcore.os.stat(path);
                        if (!S_ISDIR(sb.st_mode)) {
                            continue;
                        }
                    } catch (ErrnoException ignored) {
                        continue;
                    }
                }
                result.add(new File(path));
            }
        }

        return result;
    }

    /**
     * 创建一个dex / resource路径元素数组
     */
    private static Element[] makePathElements(List<File> files, File optimizedDirectory, List<IOException> suppressedExceptions) {
        List<Element> elements = new ArrayList<>();
        /*
         * Open all files and load the (direct or contained) dex files
         * up front.
         */
        for (File file : files) {
            File zip = null;
            File dir = new File("");
            DexFile dex = null;
            String path = file.getPath();
            String name = file.getName();
            /* zipSeparator = !/    */
            if (path.contains(zipSeparator)) {
                String split[] = path.split(zipSeparator, 2);
                zip = new File(split[0]);
                dir = new File(split[1]);
            } else if (file.isDirectory()) {//如果是目录
                // We support directories for looking up resources and native libraries.
                // Looking up resources in directories is useful for running libcore tests.
                elements.add(new Element(file, true, null, null));
            } else if (file.isFile()) {
                //如果是 .dex 文件
                if (name.endsWith(DEX_SUFFIX)) {    /*DEX_SUFFIX = .dex*/
                    // Raw dex file (not inside a zip/jar).
                    try {
                        dex = loadDexFile(file, optimizedDirectory);
                    } catch (IOException ex) {
                        System.logE("Unable to load dex file: " + file, ex);
                    }
                } else {//其他文件处理方式
                    zip = file;

                    try {
                        dex = loadDexFile(file, optimizedDirectory);
                    } catch (IOException suppressed) {
                        /*
                         * IOException might get thrown "legitimately" by the DexFile constructor if
                         * the zip file turns out to be resource-only (that is, no classes.dex file
                         * in it).
                         * Let dex == null and hang on to the exception to add to the tea-leaves for
                         * when findClass returns null.
                         */
                        suppressedExceptions.add(suppressed);
                    }
                }
            } else {
                System.logW("ClassLoader referenced unknown path: " + file);
            }
            //一个File对应一个Element，一般情况下也只会存在一个file
            if ((zip != null) || (dex != null)) {
                elements.add(new Element(dir, false, zip, dex));
            }
        }

        return elements.toArray(new Element[elements.size()]);
    }

    /**
     * Constructs a {@code DexFile} instance, as appropriate depending
     * on whether {@code optimizedDirectory} is {@code null}.
     * 加载dex文件
     */
    private static DexFile loadDexFile(File file, File optimizedDirectory)
            throws IOException {
        //如果 optimizedDirectory == null 则直接new 一个DexFile
        if (optimizedDirectory == null) {
            return new DexFile(file);
        } else {
            //否则就使用DexFile.loadDex来创建一个DexFile实例。这个方法用来构建一个odex路径，后面加上了.dex后缀
            String optimizedPath = optimizedPathFor(file, optimizedDirectory);
            //loadDex只是构建一个DexFile并返回
            return DexFile.loadDex(file.getPath(), optimizedPath, 0);
        }
    }

    /**
     * 将dex / jar文件路径和输出目录转换为关联的优化dex文件的输出文件路径。
     */
    private static String optimizedPathFor(File path,
            File optimizedDirectory) {

        String fileName = path.getName();
        if (!fileName.endsWith(DEX_SUFFIX)) {
            int lastDot = fileName.lastIndexOf(".");
            if (lastDot < 0) {
                fileName += DEX_SUFFIX;
            } else {
                StringBuilder sb = new StringBuilder(lastDot + 4);
                sb.append(fileName, 0, lastDot);
                sb.append(DEX_SUFFIX);
                fileName = sb.toString();
            }
        }

        File result = new File(optimizedDirectory, fileName);
        return result.getPath();
    }

    /**
     * Finds the named class in one of the dex files pointed at by
     * this instance. This will find the one in the earliest listed
     * path element. If the class is found but has not yet been
     * defined, then this method will define it in the defining
     * context that this instance was constructed with.
     *
     * @param name of class to find
     * @param suppressed exceptions encountered whilst finding the class
     * @return the named class or {@code null} if the class is not
     * found in any of the dex files
     */
    public Class findClass(String name, List<Throwable> suppressed) {
        for (Element element : dexElements) {
            DexFile dex = element.dexFile;
            if (dex != null) {
                Class clazz = dex.loadClassBinaryName(name, definingContext, suppressed);
                if (clazz != null) {
                    return clazz;
                }
            }
        }
        if (dexElementsSuppressedExceptions != null) {
            suppressed.addAll(Arrays.asList(dexElementsSuppressedExceptions));
        }
        return null;
    }

    /**
     * Finds the named resource in one of the zip/jar files pointed at
     * by this instance. This will find the one in the earliest listed
     * path element.
     *
     * @return a URL to the named resource or {@code null} if the
     * resource is not found in any of the zip/jar files
     */
    public URL findResource(String name) {
        for (Element element : dexElements) {
            URL url = element.findResource(name);
            if (url != null) {
                return url;
            }
        }

        return null;
    }

    /**
     * Finds all the resources with the given name, returning an
     * enumeration of them. If there are no resources with the given
     * name, then this method returns an empty enumeration.
     */
    public Enumeration<URL> findResources(String name) {
        ArrayList<URL> result = new ArrayList<URL>();

        for (Element element : dexElements) {
            URL url = element.findResource(name);
            if (url != null) {
                result.add(url);
            }
        }

        return Collections.enumeration(result);
    }

    /**
     * Finds the named native code library on any of the library
     * directories pointed at by this instance. This will find the
     * one in the earliest listed directory, ignoring any that are not
     * readable regular files.
     *
     * @return the complete path to the library or {@code null} if no
     * library was found
     */
    public String findLibrary(String libraryName) {
        String fileName = System.mapLibraryName(libraryName);

        for (Element element : nativeLibraryPathElements) {
            String path = element.findNativeLibrary(fileName);

            if (path != null) {
                return path;
            }
        }

        return null;
    }

    /**
     * Element of the dex/resource file path
     */
    /*package*/ static class Element {
        private final File dir;
        private final boolean isDirectory;
        private final File zip;
        private final DexFile dexFile;

        private ZipFile zipFile;
        private boolean initialized;

        public Element(File dir, boolean isDirectory, File zip, DexFile dexFile) {
            this.dir = dir;
            this.isDirectory = isDirectory;
            this.zip = zip;
            this.dexFile = dexFile;
        }

        @Override public String toString() {
            if (isDirectory) {
                return "directory \"" + dir + "\"";
            } else if (zip != null) {
                return "zip file \"" + zip + "\"" +
                       (dir != null && !dir.getPath().isEmpty() ? ", dir \"" + dir + "\"" : "");
            } else {
                return "dex file \"" + dexFile + "\"";
            }
        }

        public synchronized void maybeInit() {
            if (initialized) {
                return;
            }

            initialized = true;

            if (isDirectory || zip == null) {
                return;
            }

            try {
                zipFile = new ZipFile(zip);
            } catch (IOException ioe) {
                /*
                 * Note: ZipException (a subclass of IOException)
                 * might get thrown by the ZipFile constructor
                 * (e.g. if the file isn't actually a zip/jar
                 * file).
                 */
                System.logE("Unable to open zip file: " + zip, ioe);
                zipFile = null;
            }
        }

        /**
         * Returns true if entry with specified path exists and not compressed.
         *
         * Note that ZipEntry does not provide information about offset so we
         * cannot reliably check if entry is page-aligned. For now we are going
         * take optimistic approach and rely on (1) if library was extracted
         * it would have been found by the previous step (2) if library was not extracted
         * but STORED and not page-aligned the installation of the app would have failed
         * because of checks in PackageManagerService.
         */
        private boolean isZipEntryExistsAndStored(ZipFile zipFile, String path) {
            ZipEntry entry = zipFile.getEntry(path);
            return entry != null && entry.getMethod() == ZipEntry.STORED;
        }

        public String findNativeLibrary(String name) {
            maybeInit();

            if (isDirectory) {
                String path = new File(dir, name).getPath();
                if (IoUtils.canOpenReadOnly(path)) {
                    return path;
                }
            } else if (zipFile != null) {
                String entryName = new File(dir, name).getPath();
                if (isZipEntryExistsAndStored(zipFile, entryName)) {
                  return zip.getPath() + zipSeparator + entryName;
                }
            }

            return null;
        }

        public URL findResource(String name) {
            maybeInit();

            // We support directories so we can run tests and/or legacy code
            // that uses Class.getResource.
            if (isDirectory) {
                File resourceFile = new File(dir, name);
                if (resourceFile.exists()) {
                    try {
                        return resourceFile.toURI().toURL();
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            if (zipFile == null || zipFile.getEntry(name) == null) {
                /*
                 * Either this element has no zip/jar file (first
                 * clause), or the zip/jar file doesn't have an entry
                 * for the given name (second clause).
                 */
                return null;
            }

            try {
                /*
                 * File.toURL() is compliant with RFC 1738 in
                 * always creating absolute path names. If we
                 * construct the URL by concatenating strings, we
                 * might end up with illegal URLs for relative
                 * names.
                 */
                return new URL("jar:" + zip.toURL() + "!/" + name);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }//Element end


}

```