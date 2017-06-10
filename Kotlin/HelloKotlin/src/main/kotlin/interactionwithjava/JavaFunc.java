package interactionwithjava;

/**
 * @author Ztiany
 *         Email ztiany3@gmail.com
 *         Date 17.6.10 11:07
 */
public class JavaFunc {

    private String name;
    private int age;
    public String tag = "JavaFunc";

    public JavaFunc() {

    }

    public JavaFunc(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public int addAge() {
        return ++age;
    }

    public String nullString() {
        return null;
    }
}
