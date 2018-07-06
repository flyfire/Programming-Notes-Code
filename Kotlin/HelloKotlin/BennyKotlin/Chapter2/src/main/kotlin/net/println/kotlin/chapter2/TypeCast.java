package net.println.kotlin.chapter2;

/**
 * Created by benny on 2/26/17.
 */
public class TypeCast {
    public static void main(String... args) {
        Parent parent = new Parent();
        System.out.println(((Child)parent).getName());

    }
}
