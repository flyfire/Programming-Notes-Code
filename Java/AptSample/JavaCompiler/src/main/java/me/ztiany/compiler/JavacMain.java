package me.ztiany.compiler;


import com.sun.tools.javac.main.Main;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ztiany
 * Email: ztiany3@gmail.com
 */
public class JavacMain {

    private static final String APP_SOURCE_DIR = "JavaCompiler/src/main/java/me/ztiany/compiler/App.java";
    private static final String TARGET_OPTION = "-d";
    private static final String TARGET_DIR = "JavaCompiler/build/manual";


    public static void main(String... args) {
        //javaToolsInternalApi();
        //jsr199_2();
        //jsr269();
        //jsr269_TreeScanner();
        //javacInternalAPI();
        assertToIfThrow();
    }

    /**
     * 在没有引入 JSR-199 前，只能使用 javac 源码提供内部 API，这部分API不是标准JAVA的一部分：官方的警告标注：
     * 这不是任何支持的API的一部分。如果您编写依赖于此的代码，则需要您自担风险。此代码及其内部接口如有更改或删除，恕不另行通知。
     */
    private static void javaToolsInternalApi() {
        Main compiler = new Main("javac");
        System.out.println(new File(".").getAbsolutePath());
        compiler.compile(new String[]{APP_SOURCE_DIR, TARGET_OPTION, TARGET_DIR});
    }

    private static void jsr199_1() {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int run = javaCompiler.run(null, null, null, APP_SOURCE_DIR, "-d", "JavaCompiler/build/manual");
        System.out.println(run);
    }

    private static void jsr199_2() {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(diagnostics, null, null);

        File file = new File(APP_SOURCE_DIR);
        Iterable<? extends JavaFileObject> compilationUnits = standardFileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, diagnostics, Arrays.asList(TARGET_OPTION, TARGET_DIR), null, compilationUnits);
        task.call();

        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
            System.out.format("Error on line %d in %s\n%s\n", diagnostic.getLineNumber(), diagnostic.getSource().toUri(), diagnostic.getMessage(null));
        }

        try {
            standardFileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 整个类文件被扫描，包括内部类以及全部方法、构造方法和字段。注解处理在填充符号表之后进行，
     * ElementScanner 类扫描的 Element 其实就是符号 Symbol：public abstract class Symbol extends AnnoConstruct implements Element
     */
    private static void jsr269() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null);

        File file = new File(APP_SOURCE_DIR);
        Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(Arrays.asList(file));
        List<String> options = Arrays.asList(TARGET_OPTION, TARGET_DIR);

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, options, null, sources);

        VisitProcessor processor = new VisitProcessor();
        task.setProcessors(Arrays.asList(processor));

        task.call();

        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 填充符号表前一步是构造语法树。对语法树的扫描，com.sun.source.* 提供了扫描器TreeScanner。
     * 获取语法树是通过工具类 Trees 的 getTree 方法完成的。 com.sun.source.* 包下暴露的 API 对语法树只能做只读操作，
     * 功能有限，要想修改语法树必须使用 javac 的内部 API。
     */
    private static void jsr269_TreeScanner() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null);

        File file = new File(APP_SOURCE_DIR);
        Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(Arrays.asList(file));
        List<String> options = Arrays.asList(TARGET_OPTION, TARGET_DIR);

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, options, null, sources);
        VisitTreeProcessor processor = new VisitTreeProcessor();
        task.setProcessors(Arrays.asList(processor));
        task.call();

        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 内部API，修改AST
     */
    private static void javacInternalAPI() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null);

        File file = new File(APP_SOURCE_DIR);
        Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(Arrays.asList(file));
        List<String> options = Arrays.asList(TARGET_OPTION, TARGET_DIR);

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, options, null, sources);
        ModifyTreeProcessor processor = new ModifyTreeProcessor();
        task.setProcessors(Arrays.asList(processor));
        task.call();

        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void assertToIfThrow() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager manager = compiler.getStandardFileManager(diagnostics, null, null);

        File file = new File(APP_SOURCE_DIR);
        Iterable<? extends JavaFileObject> sources = manager.getJavaFileObjectsFromFiles(Arrays.asList(file));
        List<String> options = Arrays.asList(TARGET_OPTION, TARGET_DIR);

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, options, null, sources);
        ForceAssertions processor = new ForceAssertions();
        task.setProcessors(Arrays.asList(processor));
        task.call();

        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
