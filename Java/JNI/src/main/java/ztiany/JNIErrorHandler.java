package ztiany;

/**
 * @author Ztiany
 *         Email ztiany3@gmail.com
 *         Date 2017/4/9 19:39
 */
public class JNIErrorHandler {

    void run() {
        testNativeThrow();
    }

    public void occurreError() {
        int a = 8 / 0;
    }

    public native void testNativeThrow();

    public native void testJavaThrow();

}
