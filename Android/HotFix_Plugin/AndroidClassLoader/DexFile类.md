```java
/**
* DexFile表示一个Dex文件
*/
public final class DexFile {

    private Object mCookie;
    private final String mFileName;
    private final CloseGuard guard = CloseGuard.get();

    /**
     * 从给定的File对象打开一个DEX文件。这通常是一个ZIP / JAR，里面有一个“classes.dex”文件。
     *
     * VM将在/ data / dalvik-cache中生成相应文件的名称并将其打开，如果系统权限允许，
     * 可能会先创建或更新它。不要传入/ data / dalvik-cache中的文件名称，因为指定的文件应该处于其原始（pre-dexopt）状态
     *
     * @param file
     *            the File object referencing the actual DEX file
     *
     * @throws IOException
     *             if an I/O error occurs, such as the file not being found or
     *             access rights missing for opening it
     */
    public DexFile(File file) throws IOException {
        this(file.getPath());
    }

    /**
     * 从给定的File对象打开一个DEX文件。这通常是一个ZIP / JAR，里面有一个“classes.dex”文件。
     *
     *
     * @param fileName
     *            the filename of the DEX file
     *
     * @throws IOException
     *             if an I/O error occurs, such as the file not being found or
     *             access rights missing for opening it
     */
    public DexFile(String fileName) throws IOException {
        mCookie = openDexFile(fileName, null, 0);
        mFileName = fileName;
        guard.open("close");
        //System.out.println("DEX FILE cookie is " + mCookie + " fileName=" + fileName);
    }

    /**
     * 从给定的文件名打开DEX文件，使用指定的文件来保存优化的数据
     *
     * @param sourceName
     *  Jar or APK file with "classes.dex".
     * @param outputName
     *  File that will hold the optimized form of the DEX data.
     * @param flags
     *  Enable optional features.
     */
    private DexFile(String sourceName, String outputName, int flags) throws IOException {
        if (outputName != null) {
            try {
                String parent = new File(outputName).getParent();
                if (Libcore.os.getuid() != Libcore.os.stat(parent).st_uid) {
                    throw new IllegalArgumentException("Optimized data directory " + parent
                            + " is not owned by the current user. Shared storage cannot protect"
                            + " your application from code injection attacks.");
                }
            } catch (ErrnoException ignored) {
                // assume we'll fail with a more contextual error later
            }
        }

        mCookie = openDexFile(sourceName, outputName, flags);
        mFileName = sourceName;
        guard.open("close");
        //System.out.println("DEX FILE cookie is " + mCookie + " sourceName=" + sourceName + " outputName=" + outputName);
    }


    static public DexFile loadDex(String sourcePathName, String outputPathName,int flags) throws IOException {
        return new DexFile(sourcePathName, outputPathName, flags);
    }

 
    public String getName() {
        return mFileName;
    }

    @Override public String toString() {
        return getName();
    }

    public void close() throws IOException {
        if (mCookie != null) {
            guard.close();
            closeDexFile(mCookie);
            mCookie = null;
        }
    }

  
    public Class loadClass(String name, ClassLoader loader) {
        String slashName = name.replace('.', '/');
        return loadClassBinaryName(slashName, loader, null);
    }

  
    public Class loadClassBinaryName(String name, ClassLoader loader, List<Throwable> suppressed) {
        return defineClass(name, loader, mCookie, suppressed);
    }

    private static Class defineClass(String name, ClassLoader loader, Object cookie,
                                     List<Throwable> suppressed) {
        Class result = null;
        try {
            result = defineClassNative(name, loader, cookie);
        } catch (NoClassDefFoundError e) {
            if (suppressed != null) {
                suppressed.add(e);
            }
        } catch (ClassNotFoundException e) {
            if (suppressed != null) {
                suppressed.add(e);
            }
        }
        return result;
    }


    public Enumeration<String> entries() {
        return new DFEnum(this);
    }

    
    // Helper class.
    private class DFEnum implements Enumeration<String> {
        private int mIndex;
        private String[] mNameList;

        DFEnum(DexFile df) {
            mIndex = 0;
            mNameList = getClassNameList(mCookie);
        }

        public boolean hasMoreElements() {
            return (mIndex < mNameList.length);
        }

        public String nextElement() {
            return mNameList[mIndex++];
        }
    }

  
    @Override protected void finalize() throws Throwable {
        try {
            if (guard != null) {
                guard.warnIfOpen();
            }
            close();
        } finally {
            super.finalize();
        }
    }

    private static Object openDexFile(String sourceName, String outputName, int flags) throws IOException {
        // Use absolute paths to enable the use of relative paths when testing on host.
        return openDexFileNative(new File(sourceName).getAbsolutePath(), (outputName == null) ? null : new File(outputName).getAbsolutePath(),  flags);
    }

    private static native void closeDexFile(Object cookie);
    private static native Class defineClassNative(String name, ClassLoader loader, Object cookie) throws ClassNotFoundException, NoClassDefFoundError;
    private static native String[] getClassNameList(Object cookie);
   
    private static native Object openDexFileNative(String sourceName, String outputName, int flags);

    public static native boolean isDexOptNeeded(String fileName) throws FileNotFoundException, IOException;

    public static final int NO_DEXOPT_NEEDED = 0;

    public static final int DEX2OAT_NEEDED = 1;

    public static final int PATCHOAT_NEEDED = 2;

    public static final int SELF_PATCHOAT_NEEDED = 3;

    public static native int getDexOptNeeded(String fileName, String pkgname,  String instructionSet, boolean defer) throws FileNotFoundException, IOException;

}
```