package me.ztiany.lombok;

/**
 *
 * @author ztiany
 * Email: ztiany3@gmail.com
 */
public class LombokMain {

    public static void main(String... args) {
        Person person = new Person();
        person.setName("haha");
        System.out.println(person.getName());
    }

}
