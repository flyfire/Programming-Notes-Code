package ztiany;

/**
 * @author Ztiany
 *         Email ztiany3@gmail.com
 *         Date 2017/4/5 22:12
 */
public class JNIMain {

    static {
        System.loadLibrary("jni/CLibrary");
        System.loadLibrary("jni/JNIErrorHandler");
    }

    public static void main(String... args) {
//        new JavaCallC().run();
//        new CCallJava().run();
//        new JNIErrorHandler().run();// java.io.EOFException
        new RegisterMethod().run();
    }

}
