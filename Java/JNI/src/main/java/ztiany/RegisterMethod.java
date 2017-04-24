package ztiany;

/**
 * @author Ztiany
 *         Email ztiany3@gmail.com
 *         Date 2017/4/10 22:21
 */
public class RegisterMethod {

    public native String dynamicRegFromJni();     //动态方法注册

    void run() {
        System.out.println(dynamicRegFromJni());
    }
}
