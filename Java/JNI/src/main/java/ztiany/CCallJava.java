package ztiany;

/**
 * @author Ztiany
 *         Email ztiany3@gmail.com
 *         Date 2017/4/8 7:32
 */
public class CCallJava {

    private int age = 23;
    private static double salary = 30.0;


    CCallJava() {
    }

    public native void javaCallC();


    void run() {
        javaCallC();
        System.out.println(toString());
    }

    public String getName() {
        return this.getClass().getName();
    }

    public static String getPath() {
        return CCallJava.class.getPackage().getName();
    }


    @Override
    public String toString() {
        return "CCallJava{" +
                ", age=" + age +
                ", salary=" + salary + '\'' +
                '}';
    }
}
